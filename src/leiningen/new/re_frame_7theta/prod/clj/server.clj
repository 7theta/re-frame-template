(ns {{ns-name}}.server
    (:require [{{ns-name}}.app :refer [app]]
              [{{ns-name}}.handler :refer [handler]]
              [config.core :refer [env]]
              [org.httpkit.server :refer [run-server]])
    (:gen-class))

(defn -main [& args]
  (let [port (Integer/parseInt (or (env :port) "8080"))]
    (run-server (handler app) {:port port})))
