;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html)
;;   which can be found in the LICENSE file at the root of this
;;   distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns leiningen.new.re-frame-7theta
  (:require [leiningen.core.main :as main]
            [leiningen.new.templates :refer [name-to-path sanitize-ns ->files]]
            [leiningen.new.options.helpers :as helpers]
            [leiningen.new.options.base :as base]
            [leiningen.new.options.views :as views]
            [leiningen.new.options.garden :as garden]
            [leiningen.new.options.less :as less]
            [leiningen.new.options.server :as server]
            [leiningen.new.options.test :as test]
            [clojure.set :as set]))

(declare template-data check-options app-files)

;;; Public

(def available-options
  #{server/option garden/option less/option "+re-frisk" test/option})

(defn re-frame-7theta
  [name & options]
  (let [data (template-data name options)]
    (check-options options)
    (main/info "Generating re-frame project with 7theta template.")
    (apply ->files data (app-files data options))))

;;; Implementation

(defn template-data
  [name options]
  {:name name
   :ns-name (sanitize-ns name)
   :sanitized (name-to-path name)
   :server? (helpers/invoke-option server/option options)
   :re-frisk? (helpers/invoke-option "+re-frisk" options)
   :test? (helpers/invoke-option test/option options)
   :garden? (helpers/invoke-option garden/option options)
   :less? (helpers/invoke-option less/option options)})

(defn check-options
  [options]
  (let [options-set (into #{} options)]
    (when-not (set/superset? available-options options-set)
      (main/abort "\nError: invalid profile(s)\n"))))

(defn app-files
  [data options]
  (concat
   (base/files data)
   (views/files data)
   (when (helpers/option? server/option options) (server/files data))
   (when (helpers/option? garden/option options) (garden/files data))
   (when (helpers/option? less/option options) (less/files data))
   (when (helpers/option? test/option options) (test/files data))))
