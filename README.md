# re-frame-7theta

[![Current Version](https://img.shields.io/clojars/v/re-frame-7theta/lein-template.svg)](https://clojars.org/re-frame-7theta/lein-template)
[![GitHub license](https://img.shields.io/github/license/7theta/re-frame-template.svg)](LICENSE)

Leiningen template for
[reagent](https://github.com/reagent-project/reagent) web apps that
implements the [re-frame](https://github.com/Day8/re-frame) pattern. 

This template is based on [re-frame-template](https://github.com/Day8/re-frame-template).

## Usage

The base template includes:

* [re-frame](https://github.com/Day8/re-frame)
* [via](https://github.com/7theta/via)
* [shadow-cljs](http://shadow-cljs.org/)
* [cljs-devtools](https://github.com/binaryage/cljs-devtools)
    1. Open Chrome's DevTools,`Ctrl-Shift-i`;
    1. open "Settings", `F1`;
    1. Check "Enable custom formatters" under the "Console" section;
    1. close and re-open DevTools

To create an application with the base template:

```
lein new re-frame-7theta <project-name>
```

The optional profiles include:

* [authentication](https://github.com/7theta/re-frame-via) (`+auth`) implies (`+via`)
* [reflecti](https://github.com/7theta/reflecti) (`+reflecti`)
* [re-frame-10x](https://github.com/day8/re-frame-10x) (`+trace`)

To add a profile to the base template, just append the profile name (let's use `+reflecti` as an example):

```
lein new re-frame-7theta <project-name> +reflecti
```

Any combination of profiles can be added at once:

```
lein new re-frame-7theta <project-name> +test +trace
```

## Development Build

### Run application:

Install js dependencies
```
npm install
```

From a repl, run the following commands
```
user> (dev)
dev> (go)
```

Shadow-cljs will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

### Logging:

In _project-name.config_, there is a variable called `debug?`, which
defaults to _true_. However, for the `min` build, this variable is
re-defined to _false_.

When `debug?` is true, we include `(enable-console-print!)`. If you
wrap all of your `println`s with a `when` block as show below, then
you will get logs printed to the browser's console for the `dev` build
and not the `min` build. 

```clojure
(when config/debug?
  (println "dev mode"))
```

### Run tests (if using +test):

```
lein clean
lein doo phantom test once
```

The above command assumes that you have
[phantomjs](https://www.npmjs.com/package/phantomjs)
installed. However, please note that
[doo](https://github.com/bensu/doo) can be configured to run cljs.test
in many other JS environments (chrome, ie, safari, opera, slimer,
node, rhino, or nashorn). 

## Production Build

To build the uberjar release

```
npm run build-release
```

## Copyright and License

Copyright © 2015, 2016, 2017 7theta

Distributed under the Eclipse Public License.
