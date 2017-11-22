(ns {{ns-name}}.config{{#via?}}
    (:require [{{ns-name}}.msg-handler :refer [msg-handler]]
              [via.client.server-proxy]
              [via.client.router]
              [integrant.core :as ig]){{/via?}})

(def config{{^via?}} {}{{/via?}}{{#via?}}
  {:via.client/server-proxy
   nil

   :via.client/router
   {:msg-handler msg-handler
    :server-proxy (ig/ref :via.client/server-proxy)}}{{/via?}})
