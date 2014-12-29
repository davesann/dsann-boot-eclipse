# dsann-boot-eclipse

Very simple [Boot](https://github.com/boot-clj/boot) task to create eclipse .classpath file.

## Usage

```clojure
(set-env! :dependencies #(conj % '[dsann/dsann-boot-eclipse "0.3.0"]))

(require '[dsann.boot.eclipse :refer [eclipse])

(task-options!
  eclipse {:name "eclipse-project-name"})

```

```sh
$ boot eclipse -h
Generate eclipse .project and .classpath

Options:
  -h, --help       Print this help info.
  -n, --name NAME  Set the eclipse project name (mandatory parameter) to NAME.
```

Name is required either via task-options! or on command line.

```
$shell> boot eclipse
$shell> boot eclipse -n "PROJECT_NAME"

$repl> (boot eclipse)
$repl> (boot (eclipse :name "PROJECT_NAME"))

```

## Clojars

[![Clojars Project](http://clojars.org/dsann/dsann-boot-eclipse/latest-version.svg)]

## Notes

.classpath and .project files are written directly to the directory where boot is run (using spit).
 

## License

Copyright © 2014 - davesann 

Distributed under the Eclipse Public License either version 1.0.
