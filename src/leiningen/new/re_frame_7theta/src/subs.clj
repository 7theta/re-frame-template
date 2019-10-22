(ns {{ns-name}}.subs
    (:require [via.endpoint :as via]{{#auth?}}
              [via.authenticator :as auth]{{/auth?}}
              [signum.subs :refer [reg-sub subscribe]]
              [integrant.core :as ig]))

(defmethod ig/init-key :{{name}}/subs [_ _])

(reg-sub
 :api.{{ns-name}}/hello
 [#'via/interceptor{{#auth?}} #'auth/interceptor{{/auth?}}]
 (fn [query-v] "Hello from via!"))
