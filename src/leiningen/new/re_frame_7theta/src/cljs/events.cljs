(ns {{ns-name}}.events
    (:require {{#via?}}[re-frame-via.fx :as via-fx]
              [{{ns-name}}.app :refer [app]]
              [{{ns-name}}.config :refer [config]]
              {{/via?}}[{{ns-name}}.db :as db]
              [re-frame.core :refer [reg-event-db reg-event-fx]]))

(reg-event-db
 :initialize-db
 (fn [_ _]
   db/default-db))
{{#via?}}
(via-fx/register (:via.client/server-proxy @app) :timeout 5000)

(reg-event-fx
 :ws/connected
 (fn [{:keys [db]} _]
   (js/console.info "WebSocket connected")))

(reg-event-fx
 :ws/disconnected
 (fn [{:keys [db]} _]
   (js/console.info "WebSocket disconnected")))

(reg-event-fx
 :ws/reset-connecton!
 (fn [_ [_ params]]
   {:via/reset {:app app
                :config config
                :connection-params params}}))

(reg-event-fx
 :login
 (fn [{:keys [db]} _]
   {:via/dispatch {:message [:api.{{ns-name}}/login {:id "admin" :password "admin"}]
                   :on-success [:login/response]
                   :on-failure [:login/failed]}}))

(reg-event-fx
 :login/response
 (fn [{:keys [db]} [_ {:keys [token] :as login-creds}]]
   (when token
     {:dispatch [:login/succeeded login-creds]})))

(reg-event-fx
 :login/succeeded
 (fn [{:keys [db]} [_ login-creds]]
   {:db (assoc db :authenticated login-creds)
    :dispatch [:ws/reset-connecton! {:params {:token (:token login-creds)
                                              :user-id (:id login-creds)}}]}))

(reg-event-db
 :login/failed
 (fn [db error]
   (js/console.error ":login/failed" (pr-str error))
   (dissoc db :authenticated)))

(reg-event-fx
 :logout
 (fn [_ _]
   {:db db/default-db
    :dispatch [:ws/reset-connecton! nil]}))
{{/via?}}
