(ns {{ns-name}}.app
    (:require [{{ns-name}}.config :refer [config]]
              [integrant.core :as ig]))

(defonce app (atom (ig/init config)))
