(ns {{ns-name}}.views
    (:require [via.subs :as via]{{#routing?}}
              [{{ns-name}}.modules :as modules]{{/routing?}}
              [re-frame.core :refer [subscribe dispatch]]
              [reagent.core :as r]))

(defn root-panel []
  [:div
   {{#auth?}}
   [:div {:style {:margin "40px"}}
    (if @(subscribe [:{{ns-name}}/authenticated?])
      [:button {:on-click #(dispatch [:{{ns-name}}/logout])} "Logout"]
      [:button {:on-click #(dispatch [:{{ns-name}}/login])} "Login"])]
   {{/auth?}}
   {{^auth?}}
   [:div "Hello from " @(subscribe [:name])]
   {{/auth?}}

   {{#auth?}}
   (when @(subscribe [:{{ns-name}}/authenticated?])
     [:div (str @(via/subscribe [:application/hello]))])
   {{/auth?}}
   {{^auth?}}
   [:div (str @(via/subscribe [:application/hello]))]
   {{/auth?}}])

(modules/register-module! :root root-panel)
