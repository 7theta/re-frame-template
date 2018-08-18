(ns {{ns-name}}.config
    (:require {{#via?}}
              [{{ns-name}}.subs] {{/via?}}
              [{{ns-name}}.ring-handler]
              [integrant.core :as ig]))

(def config
  {{^via?}}
  {:{{ns-name}}/ring-handler {}}
  {{/via?}}
  {{#via?}}
  {
   {{#auth?}}
   :via/authenticator {:query-fn (ig/ref [:{{ns-name}}/user-store])}
   :{{ns-name}}/user-store nil
   {{/auth?}}

   :via/endpoint {}
   :via/events {:endpoint (ig/ref :via/endpoint)}
   :via/subs {:endpoint (ig/ref :via/endpoint)}
   :{{ns-name}}/ring-handler {:via-handler (ig/ref :via/endpoint)}}
  {{/via?}})

(ig/load-namespaces config)
