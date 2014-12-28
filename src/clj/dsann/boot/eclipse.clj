(ns dsann.boot.eclipse
  {:boot/export-tasks true}
  (:require
    [dsann.eclipse.dot-utils :as e]
    [clojure.java.io    :as io]
    [clojure.xml        :as xml]
    [boot.core          :as bc :refer [deftask]]
    [boot.pod           :as pod]
    [clojure.pprint     :as pprint]
    ))


(defn eclipse-cp []
  (let [env (bc/get-env)
        {:keys [source-paths resource-paths asset-paths]} env
        jars (pod/resolve-dependency-jars env)
        srcs (concat source-paths resource-paths asset-paths jars)]
    (e/classpath srcs)))


(deftask eclipse
  "Generate eclipse .project and .classpath"
  [n name NAME str "the eclipse project name (mandatory parameter)"]
  (assert name "eclipse task requires (project) name parameter")
  (bc/with-pre-wrap fileset
    (let [cp (eclipse-cp)
          cp-xml-str (with-out-str (xml/emit cp))]
      (spit (io/file ".classpath") cp-xml-str))
    (let [p (e/project name)
           p-xml-str (with-out-str (xml/emit p))]
       (spit (io/file ".project") p-xml-str))
    fileset))
