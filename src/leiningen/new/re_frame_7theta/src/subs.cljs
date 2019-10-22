(ns {{ns-name}}.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :refer [reg-sub]]))

{{#auth?}}
(reg-sub
 :{{ns-name}}/authenticated?
 (fn [db]
   (-> db :authenticated :token)))
{{/auth?}}

{{^auth?}}
(reg-sub
 :name
 (fn [db]
   (:name db)))
{{/auth?}}
