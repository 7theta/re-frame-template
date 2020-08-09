(ns {{ns-name}}.views
    (:require [via.subs :as via]
              {{#reflecti?}}[reflecti.ant-design :as antd]
              [reflecti.font-awesome :as fa]
              ["antd/lib/locale-provider/en_US" :as en-US]
              {{/reflecti?}}[re-frame.core :refer [subscribe dispatch]]
              [reagent.core :as r]))

(defn main-panel []
  (let [this (r/current-component)]
    (-> (. js/less -pageLoadFinished)
        (.then #(r/set-state this {:less-loaded true})))
    (if (:less-loaded (r/state this))
      [:div
       {{#reflecti?}}
       [antd/locale-provider
        {:locale en-US}
        {{#auth?}}
        [:div {:style {:margin "40px"}}
         (if @(subscribe [:{{ns-name}}/authenticated?])
           [antd/button {:on-click #(dispatch [:{{ns-name}}/logout])} "Logout"]
           [antd/button {:on-click #(dispatch [:{{ns-name}}/login])} "Login"])]
        {{/auth?}}
        {{^auth?}}
        [:div "Hello from " @(subscribe [:name])]
        {{/auth?}}]
       {{/reflecti?}}

       {{^reflecti?}}
       {{#auth?}}
       [:div {:style {:margin "40px"}}
        (if @(subscribe [:{{ns-name}}/authenticated?])
          [:button {:on-click #(dispatch [:{{ns-name}}/logout])} "Logout"]
          [:button {:on-click #(dispatch [:{{ns-name}}/login])} "Login"])]
       {{/auth?}}
       {{^auth?}}
       [:div "Hello from " @(subscribe [:name])]
       {{/auth?}}
       {{/reflecti?}}

       {{#auth?}}
       (when @(subscribe [:{{ns-name}}/authenticated?])
         [:div (str @(via/subscribe [:api.{{ns-name}}/hello]))])
       {{/auth?}}
       {{^auth?}}
       [:div (str @(via/subscribe [:api.{{ns-name}}/hello]))]
       {{/auth?}}]
      [:div {:style {:display "flex"
                     :position "absolute"
                     :align-items "center"
                     :justify-content "center"
                     :width "100%"
                     :height "100%"
                     :color "#108ee9"}}
       {{#reflecti?}}[fa/icon {:type :regular :name "circle-notch" :classes ["fa-spin" "fa-5x"]}]{{/reflecti?}}
       {{^reflecti?}}[:div "Loading..."]{{/reflecti?}}])))
