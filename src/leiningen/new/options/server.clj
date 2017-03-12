;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html)
;;   which can be found in the LICENSE file at the root of this
;;   distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns leiningen.new.options.server
  (:require [leiningen.new.options.helpers :as helpers]))

(defn files
  [data options]
  (concat
   [["src/clj/{{sanitized}}/handler.clj" (helpers/render "src/clj/handler.clj" data)]
    ["src/clj/{{sanitized}}/system.clj" (helpers/render "src/clj/system.clj" data)]

    ["dev/clj/user.clj" (helpers/render "dev/clj/user.clj" data)]
    ["dev/clj/dev.clj" (helpers/render "dev/clj/dev.clj" data)]
    ["dev/clj/{{sanitized}}/app.clj" (helpers/render "dev/clj/app.clj" data)]
    ["dev/clj/{{sanitized}}/dev_handler.clj" (helpers/render "dev/clj/dev_handler.clj" data)]

    ["prod/clj/{{sanitized}}/app.clj" (helpers/render "prod/clj/app.clj" data)]
    ["prod/clj/{{sanitized}}/server.clj" (helpers/render "prod/clj/server.clj" data)]

    ["src/cljs/{{sanitized}}/system.cljs" (helpers/render "src/cljs/system.cljs" data)]
    ["src/cljs/{{sanitized}}/app.cljs" (helpers/render "src/cljs/app.cljs" data)]]
   (when (options :via)
     [["src/clj/{{sanitized}}/msg_handler.clj" (helpers/render "src/clj/msg_handler.clj" data)]
      ["src/cljs/{{sanitized}}/msg_handler.cljs" (helpers/render "src/cljs/msg_handler.cljs" data)]])))
