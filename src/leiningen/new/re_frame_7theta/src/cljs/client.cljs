(ns {{ns-name}}.client
    (:require {{#via?}}[via.endpoint :refer [subscribe dispose] :as via]{{/via?}}
              [re-frame.core :refer [dispatch]]
              [integrant.core :as ig]))

{{#via?}}

(defmethod ig/init-key :{{ns-name}}/test-client
  [_ {:keys [endpoint]}]
  {:endpoint endpoint
   :sub-key (subscribe endpoint
                       {:open #(js/console.log "WebSocket Connected" (pr-str %))
                        :close #(do (js/console.log "WebSocket Disconnected" (pr-str %))
                                    (dispatch [:{{ns-name}}/logout]))})})

(defmethod ig/halt-key! :{{ns-name}}/test-client
  [_ {:keys [endpoint sub-key]}]
  (dispose endpoint sub-key))

{{/via?}}
