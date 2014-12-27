(set-env! :resource-paths #{"src/clj"})

(task-options!
  pom {:project 'dsann/dsann-boot-eclipse
       :version "0.1.0"}
  jar {})


