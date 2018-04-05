(ns {{ns-name}}.views
    (:require {{#reflecti?}}[reflecti.ant-design :as antd]
              {{/reflecti?}}[re-frame.core :refer [subscribe dispatch]]))

(defn main-panel []
  {{#reflecti?}}[antd/locale-provider
                 {:locale (.-en_US antd/locales)}
                 {{#auth?}}[:div {:style {:margin "40px"}}
                            (if @(subscribe [:authenticated?])
                              [antd/button {:on-click #(dispatch [:logout])} "Logout"]
                              [antd/button {:on-click #(dispatch [:login])} "Login"])]{{/auth?}}
                 {{^auth?}}[:div "Hello from " @(subscribe [:name])]{{/auth?}}]{{/reflecti?}}{{^reflecti?}}
  {{#auth?}}[:div {:style {:margin "40px"}}
             (if @(subscribe [:authenticated?])
               [:button {:on-click #(dispatch [:logout])} "Logout"]
               [:button {:on-click #(dispatch [:login])} "Login"])]{{/auth?}}{{^auth?}}
  [:div "Hello from " @(subscribe [:name])]{{/auth?}}{{/reflecti?}})
