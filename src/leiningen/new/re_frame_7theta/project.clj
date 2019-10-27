(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520" :exclusions [com.cognitect/transit-clj]]

                 {{#reflecti?}}[com.7theta/reflecti "2.2.3"]
                 {{/reflecti?}}[re-frame "0.10.9"]
                 [com.7theta/via "3.2.4"]

                 [integrant "0.7.0"]]
  :profiles {:dev {:source-paths ["dev"]
                   :global-vars {*warn-on-reflection* true}
                   :dependencies [[com.google.javascript/closure-compiler-unshaded "v20190729"]
                                  [org.clojure/google-closure-library "0.0-20190213-2033d5d9"]
                                  [binaryage/devtools "0.9.10"]
                                  [thheller/shadow-cljs "2.8.65"]
                                  [integrant/repl "0.3.1"]{{#trace?}}
                                  [day8.re-frame/re-frame-10x "0.4.4"]{{/trace?}}]}
             :uberjar {:source-paths ["prod"]
                       :resource-paths ["resources/public/js/compiled"]
                       :dependencies [[yogthos/config "1.1.6"]]
                       :main {{name}}.main
                       :aot :all
                       :uberjar-name "{{name}}.jar"}}
  :prep-tasks ["compile"])
