(ns {{ns-name}}.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 :name
 (fn [db]
   (:name db)))
