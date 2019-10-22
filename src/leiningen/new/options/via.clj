;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html)
;;   which can be found in the LICENSE file at the root of this
;;   distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns leiningen.new.options.via
  (:require [leiningen.new.options.helpers :as helpers]))

(defn files
  [data options]
  (concat
   [["src/{{sanitized}}/ring_handler.clj" (helpers/render "src/ring_handler.clj" data)]
    ["src/{{sanitized}}/config.clj" (helpers/render "src/config.clj" data)]

    ["src/{{sanitized}}/config.cljs" (helpers/render "src/config.cljs" data)]
    ["src/{{sanitized}}/app.cljs" (helpers/render "src/app.cljs" data)]
    ["src/{{sanitized}}/core.cljs" (helpers/render "src/core.cljs" data)]
    ["src/{{sanitized}}/subs.clj" (helpers/render "src/subs.clj" data)]
    ["src/{{sanitized}}/events.clj" (helpers/render "src/events.clj" data)]]
   (when (options :auth)
     [["src/{{sanitized}}/user_store.clj" (helpers/render "src/user_store.clj" data)]])))
