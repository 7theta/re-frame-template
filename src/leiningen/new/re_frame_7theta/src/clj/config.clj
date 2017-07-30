(ns {{ns-name}}.config{{#via?}}
    (:require [{{ns-name}}.msg-handler :refer [msg-handler]]
              [taoensso.sente.server-adapters.http-kit :refer [get-sch-adapter]]
              [integrant.core :as ig]){{/via?}})

(def config{{#via?}}
  {:via.server.client-proxy/client-proxy
   {:sente-web-server-adapter (get-sch-adapter)}

   :via.server.router/router
   {:msg-handler msg-handler
    :client-proxy (ig/ref :via.server.client-proxy/client-proxy)}}{{/via?}}{{^via?}}{}{{/via?}})

(ig/load-namespaces config)
