(ns dsann.eclipse.dot-utils
  (:require [clojure.java.io :as io]))


(defn directory? [v]
  (.isDirectory (io/as-file v)))

(defn kind [p]
  (cond
    (directory? p) "src"
    :else "lib"))

(defn path [p]
  (cond 
    (instance? String p) p
    (instance? java.io.File p) (.getPath p)))

(defn classpath
  "Create a clojure.xml eclipse classpath from a seq of paths or (jar) files"
  [paths]
  {:tag :classpath
   :content 
   (concat  
     (for [p paths]
       {:tag :classpathentry
        :attrs {:kind (kind p) :path (path p)}})
     [{:tag :classpathentry 
       :attrs {:kind "con" :path "org.eclipse.jdt.launching.JRE_CONTAINER"}}])})

