(ns {{ns-name}}.config{{#via?}}
    (:require {{#auth?}}[re-frame-via.authenticator :as auth]
              {{/auth?}}[taoensso.sente.server-adapters.http-kit :refer [get-sch-adapter]]
              [integrant.core :as ig]){{/via?}})

(def config{{^via?}} {}{{/via?}}{{#via?}}
  {:via.server/client-proxy
   {:sente-web-server-adapter (get-sch-adapter){{#auth?}}
    :user-id-fn (fn [ring-req] (-> ring-req :params :user-id)){{/auth?}}}

   :via.server/router
   {:msg-handler (ig/ref {{#auth?}}:re-frame-via/authenticated-msg-handler{{/auth?}}{{^auth?}}:{{ns-name}}/msg-handler{{/auth?}})
    :client-proxy (ig/ref :via.server/client-proxy)}{{#auth?}}

   :re-frame-via/authenticated-msg-handler
   {:authenticator (ig/ref [:re-frame-via/authenticator])
    :un-authenticated-message-set #{:api.{{ns-name}}/login}
    :msg-handler (ig/ref :{{ns-name}}/msg-handler)}

   :re-frame-via/authenticator
   {:query-fn (fn [id]
                {:id id
                 :password (auth/hash-password id)})}{{/auth?}}

   :{{ns-name}}/msg-handler
   {{#auth?}}{:authenticator (ig/ref [:re-frame-via/authenticator])}{{/auth?}}{{^auth?}}{}{{/auth?}}}{{/via?}}){{#via?}}

(ig/load-namespaces config){{/via?}}
