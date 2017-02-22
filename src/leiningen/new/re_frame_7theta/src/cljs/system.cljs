(ns {{ns-name}}.system
    (:require {{#via?}}[{{ns-name}}.msg-handler :refer [msg-handler]]
              [via.client.server-proxy :refer [server-proxy]]
              [via.client.router :refer [router]]
              {{/via?}}[com.stuartsierra.component :as component]))

(defn system
  []
  (component/system-map{{#via?}}
                       :server-proxy (server-proxy)
                       :router (component/using (router msg-handler)
                                                [:server-proxy]){{/via?}}))
