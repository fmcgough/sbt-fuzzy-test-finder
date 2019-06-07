# sbt-fuzzy-test-finder

An SBT plugin for quickly finding test suites to run with `testOnly`, using [fzf](https://github.com/junegunn/fzf).

## Usage

Provides a single command `tt`, which invokes `fzf` to let you make an interactive fuzzy search for the
test suite you want to run. When a suite is selected (by pressing enter), that test suite is run using `testOnly`.
The `testOnly` task is also appended to your history, allowing you to re-run the test easily.

## Requirements

[fzf](https://github.com/junegunn/fzf) must be installed and available on your PATH.

## Credits

Heavily inspired by [sbt-project-switcher](https://github.com/todokr/sbt-project-switcher) :heart:

## Setup

### Globally

Recommended. Just add sbt-fuzzy-test-finder to `~/.sbt/1.0/plugins/plugins.sbt`.

```
addSbtPlugin("io.github.fmcgough" % "sbt-fuzzy-test-finder" % "(version)")
```

### Per project

Add sbt-fuzzy-test-finder to `project/plugins.sbt`.

```
addSbtPlugin("io.github.fmcgough" % "sbt-fuzzy-test-finder" % "(version)")
```

## License

MIT.
