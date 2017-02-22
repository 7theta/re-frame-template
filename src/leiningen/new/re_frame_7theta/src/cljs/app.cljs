(ns {{ns-name}}.app
    (:require [{{ns-name}}.system :refer [system]]
              [com.stuartsierra.component :as component]))

(defonce app (component/start system))
