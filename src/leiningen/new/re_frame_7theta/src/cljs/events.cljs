(ns {{ns-name}}.events
    (:require {{#via?}}[via-fx.fx :as via-fx]
              [{{ns-name}}.app :refer [app]]
              {{/via?}}[{{ns-name}}.db :as db]
              [re-frame.core :refer [reg-event-db reg-event-fx]]))

{{#via?}}
(via-fx/register (:via.client/server-proxy app))

{{/via?}}
(reg-event-db
 :initialize-db
 (fn [_ _]
   db/default-db))
