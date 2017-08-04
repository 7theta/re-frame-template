(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.854"]

                 [reagent "0.7.0"]
                 [re-frame "0.9.4"]{{#garden?}}
                 [garden "1.3.2"]{{/garden?}}{{#server?}}

                 [http-kit "2.2.0"]
                 [ring/ring-core "1.6.2" :exclusions [commons-codec]]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-anti-forgery "1.1.0"]
                 [compojure "1.6.0"]{{#via?}}

                 [com.7theta/re-frame-via-fx "0.2.2"]{{/via?}}

                 [integrant "0.5.0"]
                 [yogthos/config "0.8"]{{/server?}}]
  :min-lein-version "2.5.3"{{#server?}}
  :source-paths ["src/clj"]{{/server?}}
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"{{#test?}}
                                    "test/js"{{/test?}}{{#garden?}}
                                    "resources/public/css"{{/garden?}}]
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  :profiles {:dev {:source-paths ["dev/clj"]
                   :dependencies [{{#re-frisk?}}[re-frisk "0.4.5"]
                                  {{/re-frisk?}}[ns-tracker "0.3.1"]
                                  [binaryage/devtools "0.9.4"]
                                  [figwheel-sidecar "0.5.12"]
                                  [com.cemerick/piggieback "0.2.2"]{{#server?}}
                                  [ring/ring-devel "1.6.2"]
                                  [integrant/repl "0.2.0"]{{/server?}}]
                   :plugins [[lein-cljsbuild "1.1.7" :exclusions [org.apache.commons/commons-compress]]{{#garden?}}
                             [lein-garden "0.3.0" :exclusions [org.clojure/clojure]]{{/garden?}}{{#less?}}
                             [lein-less "1.7.5"]{{/less?}}
                             [lein-figwheel "0.5.12" :exclusions [org.clojure/clojure]]{{#test?}}
                             [lein-doo "0.1.7"]{{/test?}}]}{{#server?}}
             :uberjar {:source-paths ["prod/clj"]
                       :main {{name}}.server
                       :aot [{{name}}.server]
                       :uberjar-name "{{name}}.jar"}{{/server?}}}
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs" "dev/cljs"]
                        :figwheel {:on-jsload "{{name}}.core/mount-root"}
                        :compiler {:main {{name}}.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :asset-path "js/compiled/out"
                                   :source-map-timestamp true
                                   :preloads [devtools.preload]
                                   :external-config {:devtools/config {:features-to-install :all}}}}
                       {:id "min"
                        :source-paths ["src/cljs" "prod/cljs"]
                        :jar true
                        :compiler {:main {{name}}.core
                                   :output-to "resources/public/js/compiled/app.js"
                                   :optimizations :advanced
                                   :pretty-print false}}
                       {:id "test"
                        :source-paths ["src/cljs" "dev/cljs" "test/cljs"]
                        :compiler {:main {{name}}.runner
                                   :output-to "resources/public/js/compiled/test.js"
                                   :output-dir "resources/public/js/compiled/test/out"
                                   :optimizations :none}}]}
  {{#garden?}}:garden {:builds [{:id "screen"
                                 :source-paths ["src/clj"]
                                 :stylesheet {{name}}.css/screen
                                 :compiler {:output-to "resources/public/css/screen.css"
                                            :pretty-print? true}}]}{{/garden?}}{{#less?}}
  :less {:source-paths ["less"]
         :target-path "resources/public/css"}
  {{/less?}}:prep-tasks [["cljsbuild" "once" "min"]{{#garden?}} ["garden" "once"] {{/garden?}}{{#less?}}
                         ["less" "once"]{{/less?}} "compile"])
