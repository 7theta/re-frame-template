(ns {{ns-name}}.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :refer [reg-sub]]))

{{#routing?}}
(reg-sub
 :application/route
 (fn [db]
   (:application/route db))) {{/routing?}}

{{#auth?}}
(reg-sub
 :application/authenticated?
 (fn [db]
   (-> db :authenticated :token))){{/auth?}}

{{^auth?}}
(reg-sub
 :name
 (fn [db]
   (:name db)))
{{/auth?}}
