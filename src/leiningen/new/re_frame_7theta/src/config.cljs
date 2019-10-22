(ns {{ns-name}}.config
    (:require [via.endpoint :as via]
              [via.events]
              [via.subs]
              [via.fx]
              [integrant.core :as ig]))

;;; Public

(def config
  {:via/endpoint
   {}

   :via/events
   {:endpoint (ig/ref :via/endpoint)}

   :via/subs
   {:endpoint (ig/ref :via/endpoint)}

   :via/fx
   {:endpoint (ig/ref :via/endpoint)}})
