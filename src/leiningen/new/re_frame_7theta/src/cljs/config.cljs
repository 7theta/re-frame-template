(ns {{ns-name}}.config{{#via?}}
    (:require [via.endpoint :as via]
              [via.events]
              [via.subs]
              [via.fx]
              [{{ns-name}}.client]
              [integrant.core :as ig]){{/via?}})

;;; Public

(def config
  {{^via?}}{}{{/via?}}
  {{#via?}}
  {:via/endpoint
   {}

   :via/events
   {:endpoint (ig/ref :via/endpoint)}

   :via/subs
   {:endpoint (ig/ref :via/endpoint)}

   :via/fx
   {:endpoint (ig/ref :via/endpoint)}

   :{{ns-name}}/test-client
   {:endpoint (ig/ref :via/endpoint)}}
  {{/via?}})
