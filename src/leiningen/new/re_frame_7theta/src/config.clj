(ns {{ns-name}}.config
    (:require {{#auth?}}[buddy.core.nonce :as bn]
              {{/auth?}}[integrant.core :as ig]))

{{#auth?}}
(defmethod ig/init-key :{{ns-name}}/auth-secret
  [_ _]
  (bn/random-bytes 32)){{/auth?}}

(def config
  {:via/endpoint {}

   :via/events
   {:endpoint (ig/ref :via/endpoint)}

   :via/subs
   {:endpoint (ig/ref :via/endpoint)}

   :via/http-server
   {:ring-handler (ig/ref :{{name}}/ring-handler)}

   :{{name}}/subs {}

   :{{name}}/events {}

   {{#auth?}}
   :{{name}}/user-store {}

   :{{ns-name}}/auth-secret {}

   :via-auth/id-password
   {:query-fn (ig/ref :{{name}}/user-store)
    :secret (ig/ref :{{ns-name}}/auth-secret)
    :endpoint (ig/ref :via/endpoint)}{{/auth?}}

   :{{name}}/ring-handler
   {:via-handler (ig/ref :via/endpoint)}})

(ig/load-namespaces config)
