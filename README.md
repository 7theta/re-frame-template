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

* [routing](https://github.com/metosin/reitit) (`+routing`) based on `metosin/reitit`
* [authentication](https://github.com/7theta/re-frame-via) (`+auth`) based on `via`
* [fontawesome](https://fontawesome.com/) (`+fontawesome`) - the font files must be copied in after the project has been generated
* [re-frame-10x](https://github.com/day8/re-frame-10x) (`+trace`)

To add a profile to the base template, append the profile name(s). E.g.,

```
lein new re-frame-7theta <project-name> +auth +trace
```

## Development Build

### Run application:

Install js dependencies
```
npm install
```

Build the dev dependencies
```
npm run build-dev
```

From a repl, run the following commands
```
user> (dev)
dev> (go)
```

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

Shadow-cljs will automatically push cljs changes to the browser.

## Production Build

To build the uberjar release

```
npm run build-release
```

## Copyright and License

Copyright © 2015 7theta

Distributed under the Eclipse Public License.
