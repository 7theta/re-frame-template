(ns {{ns-name}}.config{{#via?}}
    (:require [{{ns-name}}.msg-handler :refer [msg-handler]]
              [via.client.server-proxy]
              [via.client.router]
              [integrant.core :as ig]){{/via?}})

(def config{{#via?}}
  {:via.client.server-proxy/server-proxy
   nil

   :via.client.router/router
   {:msg-handler msg-handler
    :server-proxy (ig/ref :via.client.server-proxy/server-proxy)}}{{/via?}}{{^via?}}{}{{/via?}})
