(ns {{ns-name}}.user-store
    (:require [via-auth.id-password :as auth]
              [integrant.core :as ig]))

;; Dummy user-store that will authenticate if the id and password are
;; the same

(defmethod ig/init-key :{{ns-name}}/user-store [_ _]
  (fn [id]
    {:id id
     :password (auth/hash-password id)}))
