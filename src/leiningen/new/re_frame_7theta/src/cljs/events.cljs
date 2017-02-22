(ns {{ns-name}}.events
    (:require [re-frame.core :refer [reg-event-db reg-event-fx]]
              [{{ns-name}}.db :as db]))

(reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))
