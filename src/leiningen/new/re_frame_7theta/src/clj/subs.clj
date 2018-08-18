(ns {{ns-name}}.subs
    (:require [via.endpoint :as via]{{#auth?}}
              [via.authenticator :as auth]{{/auth?}}
              [signum.subs :refer [reg-sub subscribe make-signal]]))

(reg-sub
 :api.{{ns-name}}/hello
 [#'via/interceptor{{#auth?}} #'auth/interceptor{{/auth?}}]
 (fn [query-v] (atom "Hello from via!"))
 (fn [value query-v] value))
