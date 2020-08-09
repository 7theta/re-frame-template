(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773"
                  :exclusions
                  [com.google.javascript/closure-compiler-unshaded
                   org.clojure/google-closure-library
                   org.clojure/google-closure-library-third-party
                   com.cognitect/transit-clj]]

                 {{#reflecti?}}[com.7theta/reflecti "2.4.0"]
                 {{/reflecti?}}[re-frame "1.0.0"]
                 [com.7theta/via "4.2.0"]

                 [integrant "0.8.0"]]
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[binaryage/devtools "1.0.2"]
                                  [thheller/shadow-cljs "2.10.21"]
                                  [integrant/repl "0.3.1"]{{#trace?}}
                                  [day8.re-frame/re-frame-10x "0.7.0"]{{/trace?}}]}
             :uberjar {:source-paths ["prod"]
                       :resource-paths ["resources/public/js/compiled"]
                       :dependencies [[yogthos/config "1.1.7"]]
                       :main {{name}}.main
                       :aot :all
                       :uberjar-name "{{name}}.jar"}}
  :prep-tasks ["compile"])
