(ns {{ns-name}}.app
    (:require [{{ns-name}}.config :refer [config]]
              [integrant.core :as ig]))

(defonce app (ig/init config))

(defn reset-app!
  []
  (ig/halt! app)
  (set! app (ig/init config)))
