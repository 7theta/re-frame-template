
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
  [data options]
  [["README.md" (helpers/render "README.md" data)]
   ["project.clj" (helpers/render "project.clj" data)]
   ["package.json" (helpers/render "package.json" data)]
   ["shadow-cljs.edn" (helpers/render "shadow-cljs.edn" data)]
   [".gitignore" (helpers/render "gitignore" data)]

   ["resources/public/index.html" (helpers/render "resources/public/index.html" data)]
   ["resources/public/js/less.min.js" (helpers/render "resources/public/js/less.min.js" data)]

   ["src/{{sanitized}}/core.cljs" (helpers/render "src/core.cljs" data)]
   ["src/{{sanitized}}/setup.cljs" (helpers/render "src/setup.cljs" data)]
   ["src/{{sanitized}}/db.cljs" (helpers/render "src/db.cljs" data)]
   ["src/{{sanitized}}/subs.cljs" (helpers/render "src/subs.cljs" data)]
   ["src/{{sanitized}}/events.cljs" (helpers/render "src/events.cljs" data)]

   ["dev//user.clj" (helpers/render "dev/user.clj" data)]
   ["dev/dev.clj" (helpers/render "dev/dev.clj" data)]
   ["prod/{{sanitized}}/main.clj" (helpers/render "prod/main.clj" data)]])
