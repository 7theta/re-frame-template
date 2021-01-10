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
            [leiningen.new.options.via :as via]
            [clojure.set :as set]
            [clojure.string :as st]))

(declare template-data check-options app-files)

;;; Public

(def available-options
  #{"+routing" "+auth" "+fontawesome" "+trace"})

(defn re-frame-7theta
  [name & options]
  (check-options options)
  (let [options (->> options
                     (filter (partial available-options))
                     (map #(keyword (apply str (rest %))))
                     set)
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
   :routing? (helpers/option-renderer options :routing)
   :auth? (helpers/option-renderer options :auth)
   :fontawesome? (helpers/option-renderer options :fontawesome)
   :trace? (helpers/option-renderer options :trace)})

(defn app-files
  [data options]
  (concat
   (base/files data options)
   (views/files data options)
   (via/files data options)))
