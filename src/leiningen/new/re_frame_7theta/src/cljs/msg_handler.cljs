(ns {{ns-name}}.msg-handler)

(defmulti msg-handler :id)

(defmethod msg-handler :chsk/state
  [{:as ev-msg :keys [?data]}]
  (let [[old-state new-state] ?data]
    (if (:open? new-state)
      (js/console.log "Socket connected")
      (js/console.log "Socket disconnected"))))

(defmethod msg-handler :chsk/recv
  [{:as ev-msg :keys [?data]}]
  (js/console.log "Server Push: " (str ?data)))

(defmethod msg-handler :chsk/handshake
  [{:as ev-msg :keys [?data]}]
  (let [[?uid ?csrf-token ?handshake-data] ?data]
    (js/console.log "Handshake: " (str ?data))))

(defmethod msg-handler :default
  [{:as ev-msg :keys [event]}]
  (js/console.log "Unhandled event: " (str event)))
