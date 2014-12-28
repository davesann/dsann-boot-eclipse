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

;; using format compatible with clojure.xml 
;;  - even though it's harder to read than hiccup style.

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

;; eclipse requires a name in the .project in order to enable "import existing project".
;;  once this has occured eclipse seems to ignore this name.
(defn project 
 "create basic .project file"
 [project-name]
 {:tag :projectDescription
  :content [{:tag :name :content [project-name]}
            {:tag :projects}
            {:tag :buildSpec
            :content [{:tag :buildCommand
                       :content [{:tag :name :content ["ccw.builder"]}
                                 {:tag :arguments}]}
                      {:tag :buildCommand
                       :content [{:tag :name :content ["org.eclipse.jdt.core.javabuilder"]}
                                 {:tag :arguments}]}
                      ]}
            {:tag :natures
             :content [{:tag :nature :content ["ccw.nature"]}
                       {:tag :nature :content ["org.eclipse.jdt.core.javanature"]}]}
           
            ]})
