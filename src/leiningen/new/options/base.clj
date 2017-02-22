;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html)
;;   which can be found in the LICENSE file at the root of this
;;   distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns leiningen.new.options.base
  (:require [leiningen.new.options.helpers :as helpers]))

(defn files
  [data]
  [["README.md" (helpers/render "README.md" data)]
   ["project.clj" (helpers/render "project.clj" data)]
   [".gitignore" (helpers/render ".gitignore" data)]

   ["resources/public/index.html" (helpers/render "resources/public/index.html" data)]

   ["src/cljs/{{sanitized}}/core.cljs" (helpers/render "src/cljs/core.cljs" data)]
   ["src/cljs/{{sanitized}}/config.cljs" (helpers/render "src/cljs/config.cljs" data)]
   ["src/cljs/{{sanitized}}/db.cljs" (helpers/render "src/cljs/db.cljs" data)]
   ["src/cljs/{{sanitized}}/subs.cljs" (helpers/render "src/cljs/subs.cljs" data)]
   ["src/cljs/{{sanitized}}/events.cljs" (helpers/render "src/cljs/events.cljs" data)]

   ["dev/cljs/{{sanitized}}/setup.cljs" (helpers/render "dev/cljs/setup.cljs" data)]
   ["prod/cljs/{{sanitized}}/setup.cljs" (helpers/render "prod/cljs/setup.cljs" data)]])
