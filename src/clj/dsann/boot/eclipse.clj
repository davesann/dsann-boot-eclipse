(ns dsann.boot.eclipse
  {:boot/export-tasks true}
  (:require
    [dsann.eclipse.dot-utils :as e]
    [clojure.java.io    :as io]
    [clojure.xml        :as xml]
    [boot.core          :as bc :refer [deftask]]
    [boot.pod           :as pod]
    ))

(defn eclipse-cp [fileset]
  (let [env (bc/get-env)
        {:keys [source-paths resource-paths asset-paths]} env
        jars (pod/resolve-dependency-jars env)
        srcs (concat source-paths resource-paths asset-paths jars)]
    (e/classpath srcs)))

(deftask eclipse
  "generate eclipse classpath"
  []
  (bc/with-pre-wrap fileset
    (let [cp (eclipse-cp fileset)
          cp-xml-str (with-out-str (xml/emit cp))]
      (spit (io/file ".classpath") cp-xml-str))
    fileset))
