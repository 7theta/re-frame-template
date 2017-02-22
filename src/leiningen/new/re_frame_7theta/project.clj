(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.456"]

                 [reagent "0.6.0"]
                 [re-frame "0.9.1"]{{#garden?}}
                 [garden "1.3.2"]{{/garden?}}{{#server?}}

                 [http-kit "2.2.0"]
                 [ring/ring-core "1.5.1"]
                 [ring/ring-defaults "0.2.3"]
                 [ring/ring-anti-forgery "1.0.1"]
                 [compojure "1.5.2"]

                 [com.7theta/via "0.3.0"]

                 [com.stuartsierra/component "0.3.2"]
                 [yogthos/config "0.8"]{{/server?}}]
  :plugins [[lein-cljsbuild "1.1.4"]{{#garden?}}
            [lein-garden "0.2.8"]{{/garden?}}{{#less?}}
            [lein-less "1.7.5"]{{/less?}}]
  :min-lein-version "2.5.3"{{#server?}}
  :source-paths ["src/clj"]{{/server?}}
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"{{#test?}}
                                    "test/js"{{/test?}}{{#garden?}}
                                    "resources/public/css"{{/garden?}}]
  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
  :figwheel {:css-dirs ["resources/public/css"]{{#server?}}
             :ring-handler {{name}}.dev-handler/dev-handler{{/server?}}}
  :profiles {:dev {:source-paths ["dev/clj"]
                   :dependencies [{{#re-frisk?}}[re-frisk "0.3.2"]
                                  {{/re-frisk?}}[ns-tracker "0.3.0"]
                                  [binaryage/devtools "0.8.2"]
                                  [figwheel-sidecar "0.5.7"]
                                  [com.cemerick/piggieback "0.2.1"]
                                  [ring/ring-devel "1.5.1"]]
                   :plugins [[lein-figwheel "0.5.7"]{{#test?}}
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
                                   :closure-defines {goog.DEBUG false}
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
