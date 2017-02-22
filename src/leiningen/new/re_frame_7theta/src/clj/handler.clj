(ns {{ns-name}}.handler
    (:require {{#via?}}[via.defaults :refer [default-sente-endpoint]]
              {{/via?}}[compojure.core :refer [GET POST defroutes]]
              [compojure.route :refer [resources]]
              [ring.util.response :refer [resource-response content-type]]
              [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
              [ring.middleware.anti-forgery :refer [wrap-anti-forgery]]
              [ring.middleware.session :refer [wrap-session]]))

(defn handler
  [app]
  (-> (let [{{#via?}}client-proxy (:client-proxy app)
            ring-ajax-get-or-ws-handshake (:ring-ajax-get-or-ws-handshake-fn client-proxy)
            ring-ajax-post (:ring-ajax-post-fn client-proxy){{/via?}}]
        (defroutes routes
          (GET "/" [] (content-type
                       (resource-response "index.html" {:root "public"})
                       "text/html")){{#via?}}
          (GET  default-sente-endpoint ring-req (ring-ajax-get-or-ws-handshake ring-req))
          (POST default-sente-endpoint ring-req (ring-ajax-post ring-req)){{/via?}}
          (resources "/")))
      (wrap-defaults site-defaults)
      wrap-anti-forgery
      wrap-session))
