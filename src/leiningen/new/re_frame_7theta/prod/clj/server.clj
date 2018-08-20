(ns {{ns-name}}.server
    (:require [{{ns-name}}.config :refer [config]]
              [config.core :refer [env]]
              [org.httpkit.server :refer [run-server]]
              [integrant.core :as ig])
    (:gen-class))

(defn -main [& args]
  (let [port (Integer/parseInt (or (env :port) "8080"))
        app (ig/init config)]
    (run-server (:{{ns-name}}/ring-handler app) {:port port})))
