(ns {{ns-name}}.msg-handler
    (:require {{#auth?}}[re-frame-via.authenticator :as auth]
              {{/auth?}}[integrant.core :as ig]))

(defmulti msg-handler (fn [opts message] (:id message)))

(defmethod ig/init-key :{{ns-name}}/msg-handler
  [_ opts]
  (fn [message] (msg-handler opts message))){{#auth?}}


(defmethod msg-handler :api.{{ns-name}}/login
  [{:keys [authenticator] :as opts} {:keys [?data ?reply-fn]}]
  (when ?reply-fn
    (?reply-fn (auth/create-token authenticator (:id ?data) (:password ?data))))){{/auth?}}

(defmethod msg-handler :default
  [_ {:keys [event ?reply-fn]}]
  (when ?reply-fn (?reply-fn {:via/unhandled-event-echoed-from-the-server event})))
