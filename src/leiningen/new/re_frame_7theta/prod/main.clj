(ns {{name}}.main
  (:require [{{name}}.config :refer [config]]
            [config.core :refer [env]]
            [integrant.core :as ig])
  (:import [java.net ServerSocket])
  (:gen-class))

(defn- allocate-free-port!
  []
  (let [socket (ServerSocket. 0)]
    (.setReuseAddress socket true)
    (let [port (.getLocalPort socket)]
      (try (.close socket) (catch Exception _))
      port)))

(defn -main [& args]
  (let [port (or (env :port) (allocate-free-port!))
        app (ig/init (assoc-in config [:via/http-server :port] port))]
    (println (str "URL: http://localhost:" port))))
