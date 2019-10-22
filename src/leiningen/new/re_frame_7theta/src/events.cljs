(ns {{ns-name}}.events
    (:require [{{ns-name}}.app :refer [reset-app!]]
              [{{ns-name}}.db :as db]
              [via.fx]
              [re-frame.core :refer [reg-event-db reg-event-fx]]))

(reg-event-db
 :initialize-db
 (fn [_ _]
   db/default-db))

{{#auth?}}
(reg-event-fx
 :{{ns-name}}/login
 (fn [{:keys [db]} _]
   {:via/dispatch
    {:event [:via/id-password-login {:id "admin" :password "admin"}]
     :on-success [:{{ns-name}}.login/succeeded]
     :on-failure [:{{ns-name}}.login/failed]
     :on-timeout [:{{ns-name}}.login/timed-out]}}))

(reg-event-fx
 :{{ns-name}}.login/succeeded
 (fn [{:keys [db]} [_ login-creds]]
   {:db (assoc db :authenticated login-creds)}))

(reg-event-db
 :{{ns-name}}.login/failed
 (fn [db error]
   (js/console.error "{{ns-name}}.login/failed" (pr-str error))
   (dissoc db :authenticated)))

(reg-event-db
 :{{ns-name}}.login/timed-out
 (fn [db error]
   (js/console.error "{{ns-name}}.login/timed-out" (pr-str error))
   (dissoc db :authenticated)))

(reg-event-fx
 :{{ns-name}}/logout
 (fn [_ _]
   (reset-app!)
   {:dispatch [:initialize-db]}))
{{/auth?}}
