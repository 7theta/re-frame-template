(ns {{ns-name}}.setup{{#re-frisk?}}
    (:require [re-frisk.core :refer [enable-re-frisk!]]){{/re-frisk?}})

(defn setup-env []
  (enable-console-print!){{#re-frisk?}}
  (enable-re-frisk!){{/re-frisk?}}
  (println "Running in dev mode"))
