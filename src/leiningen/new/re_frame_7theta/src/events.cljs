(ns {{ns-name}}.events
    (:require [{{ns-name}}.app :refer [reset-app!]]
              [{{ns-name}}.db :as db]
              [via.fx]
              [re-frame.core :refer [reg-event-db reg-event-fx]]))

(reg-event-db
 :initialize-db
 (fn [_ _]
   db/default-db))

{{#routing?}}
(reg-event-fx
 :application/route-updated
 (fn [{:keys [db]} [_ route]]
   {:db (assoc db :application/route route)})){{/routing?}}

{{#auth?}}
(reg-event-fx
 :application/login
 (fn [{:keys [db]} _]
   {:via/dispatch
    {:event [:via-auth/id-password-login {:id "admin" :password "admin"}]
     :on-success [:application.login/succeeded]
     :on-failure [:application.login/failed]
     :on-timeout [:application.login/timed-out]}}))

(reg-event-fx
 :application.login/succeeded
 (fn [{:keys [db]} [_ login-creds]]
   {:db (assoc db :authenticated login-creds)}))

(reg-event-db
 :application.login/failed
 (fn [db error]
   (js/console.error "application.login/failed" (pr-str error))
   (dissoc db :authenticated)))

(reg-event-db
 :application.login/timed-out
 (fn [db error]
   (js/console.error "application.login/timed-out" (pr-str error))
   (dissoc db :authenticated)))

(reg-event-fx
 :application/logout
 (fn [_ _]
   (reset-app!)
   {:dispatch [:initialize-db]}))
{{/auth?}}
