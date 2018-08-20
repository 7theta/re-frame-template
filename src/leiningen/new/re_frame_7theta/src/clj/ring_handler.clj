(ns {{ns-name}}.ring-handler
    (:require {{#via?}}[via.defaults :refer [default-via-endpoint]]{{/via?}}
              [compojure.core :as compojure :refer [GET POST]]
              [compojure.route :as route]
              [ring.util.response :as response]
              [ring.middleware.defaults :as ring-defaults]
              [integrant.core :as ig]))

(defmethod ig/init-key :{{ns-name}}/ring-handler [_ {:keys [{{#via?}}via-handler{{/via?}}]}]
  (-> (compojure/routes
       (GET "/" req-req (response/content-type
                         (response/resource-response "public/index.html")
                         "text/html"))
       {{#via?}}(GET default-via-endpoint ring-req (via-handler ring-req)){{/via?}}
       (route/resources "/"))
      (ring-defaults/wrap-defaults ring-defaults/site-defaults)))
