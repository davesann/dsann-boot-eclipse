(def VERSION "0.2.0")

(set-env! :resource-paths #{"src/clj"})

(task-options!
  pom {:project 'dsann/dsann-boot-eclipse
       :version VERSION}
  jar {})

(deftask install-jar
  "Build jar and install to local repo."
  []
  (comp (pom) (jar) (install)))
