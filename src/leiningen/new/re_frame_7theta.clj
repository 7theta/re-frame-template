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
            [clojure.set :as set]
            [clojure.string :as st]))

(declare template-data check-options app-files)

;;; Public

(def available-options
  #{"+server" "+via" "+auth" "+reflecti" "+trace" "+test" "+garden" "+less"})

(defn re-frame-7theta
  [name & options]
  (check-options options)
  (let [options (->> options
                     (filter (partial available-options))
                     (map #(keyword (apply str (rest %))))
                     set)
        options (cond-> options
                  (options :auth) (conj :via)
                  (options :via) (conj :server))
        data (template-data name options)]
    (main/info "Generating re-frame project with 7theta template.")
    (apply ->files data (app-files data options))))

;;; Implementation

(defn check-options
  [options]
  (let [options-set (into #{} options)]
    (when-not (set/superset? available-options options-set)
      (main/abort (str "\nError: invalid option(s)\nAvailable: "
                       (st/join " " (sort available-options)) "\n")))))

(defn template-data
  [name options]
  {:name name
   :ns-name (sanitize-ns name)
   :sanitized (name-to-path name)
   :server? (helpers/option-renderer options :server)
   :via? (helpers/option-renderer options :via)
   :auth? (helpers/option-renderer options :auth)
   :reflecti? (helpers/option-renderer options :reflecti)
   :trace? (helpers/option-renderer options :trace)
   :test? (helpers/option-renderer options :test)
   :garden? (helpers/option-renderer options :garden)
   :less? (helpers/option-renderer options :less)})

(defn app-files
  [data options]
  (concat
   (base/files data options)
   (views/files data options)
   (when (options :server) (server/files data options))
   (when (options :test) (test/files data options))
   (when (options :garden) (garden/files data options))
   (when (options :less) (less/files data options))))
