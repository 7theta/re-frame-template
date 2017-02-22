(ns {{ns-name}}.dev-handler
    (:require [{{ns-name}}.app :refer [app]]
              [{{ns-name}}.handler :refer [handler]]
              [ring.middleware.reload :refer [wrap-reload]]))

(def dev-handler (-> (handler app) wrap-reload))
