;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html)
;;   which can be found in the LICENSE file at the root of this
;;   distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns leiningen.new.options.test
  (:require [leiningen.new.options.helpers :as helpers]))

(def option "+test")

(defn files
  [data]
  [["test/cljs/{{sanitized}}/runner.cljs" (helpers/render "test/cljs/runner.cljs" data)]
   ["test/cljs/{{sanitized}}/core_test.cljs" (helpers/render "test/cljs/core_test.cljs" data)]])
