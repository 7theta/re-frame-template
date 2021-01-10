(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773"
                  :exclusions
                  [com.google.javascript/closure-compiler-unshaded
                   org.clojure/google-closure-library
                   org.clojure/google-closure-library-third-party
                   com.cognitect/transit-clj]]

                 [re-frame "1.1.2"]
                 [com.7theta/via "6.2.2"]{{#auth?}}
                 [com.7theta/via-auth "0.2.0"]{{/auth?}}{{#routing?}}
                 [metosin/reitit-frontend "0.5.10"]{{/routing?}}

                 [integrant "0.8.0"]]
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[binaryage/devtools "1.0.2"]
                                  [thheller/shadow-cljs "2.11.12"]
                                  [integrant/repl "0.3.2"]{{#trace?}}
                                  [day8.re-frame/re-frame-10x "0.7.0"]{{/trace?}}]}
             :uberjar {:source-paths ["prod"]
                       :resource-paths ["resources/public/js/compiled"]
                       :dependencies [[yogthos/config "1.1.7"]]
                       :main {{name}}.main
                       :aot :all
                       :uberjar-name "{{name}}.jar"}}
  :prep-tasks ["compile"])
