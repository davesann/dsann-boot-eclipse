# dsann-boot-eclipse

Very simple [Boot](https://github.com/boot-clj/boot) task to create eclipse .classpath file.

## Usage

```clojure
(set-env! :dependencies #(conj % '[dsann/dsann-boot-eclipse "0.1.0"]))

(require '[dsann.boot.eclipse :refer [eclipse])
```

shell> boot eclipse

repl> (boot (eclipse))

(.classpath file is written directly to the directory where boot is run (using spit). This is necessary because boot filesets write only to target/... If anyone knows a better way todo this please advise.) 

## License

Copyright © 2014 - davesann 

Distributed under the Eclipse Public License either version 1.0.
