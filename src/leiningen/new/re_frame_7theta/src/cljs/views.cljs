(ns {{ns-name}}.views
    (:require [re-frame.core :refer [subscribe dispatch]]))

(defn main-panel []
  (let [name (subscribe [:name])]
    (fn []
      [:div "Hello from " @name])))
