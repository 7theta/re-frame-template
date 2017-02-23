(ns {{ns-name}}.system
    (:require {{#via?}}[{{ns-name}}.msg-handler :refer [msg-handler]]
              [via.server
               [client-proxy :refer [client-proxy]]
               [router :refer [router]]]
              [taoensso.sente.server-adapters.http-kit :refer [sente-web-server-adapter]]
              {{/via?}}[com.stuartsierra.component :as component]))

(defn system
  []
  (component/system-map{{#via?}}
                       :client-proxy (client-proxy sente-web-server-adapter)
                       :router (component/using (router msg-handler)
                                                [:client-proxy]){{/via?}}))