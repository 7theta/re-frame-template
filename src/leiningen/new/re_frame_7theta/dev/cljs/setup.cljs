(ns {{ns-name}}.setup
    (:require [{{ns-name}}.config :as config]{{#re-frisk?}}
              [re-frisk.core :refer [enable-re-frisk!]]{{/re-frisk?}}))

(defn setup-env []
  (when config/debug?
    (enable-console-print!){{#re-frisk?}}
    (enable-re-frisk!){{/re-frisk?}}
    (println "dev mode")))
