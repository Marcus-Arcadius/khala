(ns khala.dev
  (:require [khala.core :refer :all]))

(use '[clojure.java.shell :only [sh]])

(defn open-khala []
  (start-khala 9897)
  (sh "firefox" "http:/127.0.0.1:9897"))
