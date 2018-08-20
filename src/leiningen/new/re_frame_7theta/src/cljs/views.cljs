(ns {{ns-name}}.views
    (:require {{#via?}}[via.subs :as via]{{/via?}}
              {{#reflecti?}}[reflecti.ant-design :as antd]{{/reflecti?}}
              [re-frame.core :refer [subscribe dispatch]]))

(defn main-panel []
  [:div
   {{#reflecti?}}
   [antd/locale-provider
    {:locale (.-en_US antd/locales)}
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

   {{#via?}}
   {{#auth?}}
   (when @(subscribe [:{{ns-name}}/authenticated?])
     [:div (str @(via/subscribe [:api.{{ns-name}}/hello]))])
   {{/auth?}}
   {{^auth?}}
   [:div (str @(via/subscribe [:api.{{ns-name}}/hello]))]
   {{/auth?}}
   {{/via?}}])
