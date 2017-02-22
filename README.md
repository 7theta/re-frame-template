# re-frame-7theta

[![Current Version](https://img.shields.io/clojars/v/re-frame-7theta/lein-template.svg)](https://clojars.org/re-frame-7theta/lein-template)
[![GitHub license](https://img.shields.io/github/license/7theta/re-frame-template.svg)](LICENSE)

Leiningen template
for [reagent](https://github.com/reagent-project/reagent) web apps
that implements the [re-frame](https://github.com/Day8/re-frame)
pattern.

This template is based on [re-frame-template](https://github.com/Day8/re-frame-template).

## Usage

The base template includes:

* [re-frame](https://github.com/Day8/re-frame)
* [figwheel](https://github.com/bhauman/lein-figwheel)
* [cljs-devtools](https://github.com/binaryage/cljs-devtools)
    * To enable: 1) Open Chrome's DevTools,`Ctrl-Shift-i`; 2) open "Settings", `F1`; 3) Check "Enable custom formatters" under the "Console" section; 4) close and re-open DevTools

To create an application with the base template:

```
lein new re-frame-7theta <project-name>
```

The optional profiles include:

* [compojure](https://github.com/weavejester/compojure) (`+server`)
* [via](https://github.com/7theta/via) (`+via`) implies (`+server`)
* [cljs.test](https://github.com/clojure/clojurescript/blob/master/src/main/cljs/cljs/test.cljs) and [doo](https://github.com/bensu/doo) (`+test`)
* [garden](https://github.com/noprompt/garden) (`+garden`)
* [less](https://github.com/montoux/lein-less) (`+less`)
* [re-frisk](https://github.com/flexsurfer/re-frisk) (`+re-frisk`)

To add a profile to the base template, just append the profile name (let's use `+server` as an example):

```
lein new re-frame-7theta <project-name> +server
```

Any combination of profiles can be added at once:

```
lein new re-frame-7theta <project-name> +garden +test +less +re-frisk
```

## Development Mode

### Start Cider from Emacs (if using +cider):

Put this in your Emacs config file:

```
(setq cider-cljs-lein-repl "(do (use 'figwheel-sidecar.repl-api) (start-figwheel!) (cljs-repl))")
```

Navigate to a clojurescript file and start a figwheel REPL with `cider-jack-in-clojurescript` or (`C-c M-J`)

### Compile css (if using +garden or +less):

Compile css file once.

```
lein garden once
```

or

```
lein less once
```

Automatically recompile css file on change.

```
lein garden auto
```

or

```
lein less auto
```

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

### Logging:

In _project-name.config_, there is a variable called `debug?`, which
defaults to _true_. However, for the `min` build, this variable is
re-defined to _false_.

When `debug?` is true, we include `(enable-console-print!)`. If you wrap all of your `println`s with a `when` block as show below, then you will get logs printed to the browser's console for the `dev` build and not the `min` build.

```clojure
(when config/debug?
  (println "dev mode"))
```

### Run tests (if using +test):

```
lein clean
lein doo phantom test once
```

The above command assumes that you have [phantomjs](https://www.npmjs.com/package/phantomjs) installed. However, please note that [doo](https://github.com/bensu/doo) can be configured to run cljs.test in many other JS environments (chrome, ie, safari, opera, slimer, node, rhino, or nashorn).

## Production Build

To compile clojurescript to javascript:

```
lein clean
lein cljsbuild once min
```

If you're using +handler

```
lein clean
lein uberjar
```

## Copyright and License

Copyright © 2015, 2016, 2017 7theta

Distributed under the Eclipse Public License.


