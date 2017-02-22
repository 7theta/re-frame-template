(ns {{ns-name}}.msg-handler)

(defmulti msg-handler :id)

(defmethod msg-handler :default
  [{:as ev-msg :keys [event id ?data ring-req ?reply-fn send-fn]}]
  (let [session (:session ring-req)
        uid     (:uid     session)]
    (when ?reply-fn
      (?reply-fn {:via/unhandled-event-echoed-from-the-server event}))))
