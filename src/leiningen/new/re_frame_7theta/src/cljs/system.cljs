(ns {{ns-name}}.system
    (:require [{{ns-name}}.msg-handler :refer [msg-handler]]
              [via.client.server-proxy :refer [server-proxy]]
              [via.client.router :refer [router]]
              [com.stuartsierra.component :as component]))

(defn system
  []
  (component/system-map
   :server-proxy (server-proxy)
   :router (component/using (router msg-handler)
                            [:server-proxy])))
