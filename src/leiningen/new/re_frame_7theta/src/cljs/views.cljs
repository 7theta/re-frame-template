(ns {{ns-name}}.views
    (:require {{#reflecti?}}[reflecti.ant-design :as antd]
              {{/reflecti?}}[re-frame.core :refer [subscribe dispatch]]))

(defn main-panel []
  (let [name (subscribe [:name])]
    (fn []
      {{#reflecti?}}[antd/locale-provider
                     {:locale (.-en_US antd/locales)}
                     [:div "Hello from " @name]]{{/reflecti?}}{{^reflecti?}}
      [:div "Hello from " @name]{{/reflecti?}})))
