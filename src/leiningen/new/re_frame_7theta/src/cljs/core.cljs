(ns {{ns-name}}.core
    (:require {{#server?}}[{{ns-name}}.app :refer [app]]{{/server?}}
              [{{ns-name}}.setup :refer [setup-env]]
              [{{ns-name}}.events]
              [{{ns-name}}.subs]
              [{{ns-name}}.views :as views]
              [reagent.core :as reagent]
              [re-frame.core :as re-frame]))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel] (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (setup-env)
  (mount-root))
