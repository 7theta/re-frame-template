(ns {{ns-name}}.core
    (:require [{{ns-name}}.app :refer [app]]
              [{{ns-name}}.setup :refer [setup-env]]
              [{{ns-name}}.events]
              [{{ns-name}}.subs]
              [{{ns-name}}.views :refer [root-panel]]{{#routing?}}
              [reitit.frontend :as rf]
              [reitit.frontend.easy :as rfe]{{/routing?}}
              [reagent.dom :as reagent]
              [re-frame.core :as re-frame]))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [root-panel] (.getElementById js/document "app")))

{{#routing?}}
(def routes
  [["/"
    {:name :root
     :module :root}]])

(defn init-routing
  []
  (rfe/start!
   (rf/router routes {})
   #(re-frame/dispatch [:application/route-updated %])
   {:use-fragment true})){{/routing?}}

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db]){{#routing?}}
  (init-routing){{/routing?}}
  (setup-env)
  (mount-root))
