(ns khala.dev)

(use '[clojure.java.shell :only [sh]])

(defn open-khala []
  (sh "firefox" "http:/127.0.0.1:9897"))
