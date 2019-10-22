(ns {{ns-name}}.config
    (:require [integrant.core :as ig]))

(def config
  {:via/endpoint
   {}

   :via/events
   {:endpoint (ig/ref :via/endpoint)}

   :via/subs
   {:endpoint (ig/ref :via/endpoint)}

   :via/http-server
   {:ring-handler (ig/ref :{{name}}/ring-handler)}


   :{{name}}/subs
   {}

   :{{name}}/events
   {}

   {{#auth?}}
   :{{name}}/user-store
   {}

   :via/authenticator
   {:query-fn (ig/ref [:{{name}}/user-store])}
   {{/auth?}}

   :{{name}}/ring-handler
   {:via-handler (ig/ref :via/endpoint)}})

(ig/load-namespaces config)
