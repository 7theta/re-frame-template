(ns {{ns-name}}.modules
    (:require [reagent.core :as r]))

(defonce modules (r/atom {}))

(defn register-module!
  [module render]
  (swap! modules assoc-in [module :render] render))

(defn render
  [{:keys [module props]}]
  (when-let [render (:render (get @modules module))]
    [render props]))
