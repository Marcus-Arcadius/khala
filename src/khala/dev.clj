(ns khala.dev
  (:require [khala.core :refer :all]
            [khala.utils :as u]
            [khala.mount :as mount]))

(use '[clojure.java.shell :only [sh]])

(defn open-khala []
  (start-khala 9897)
  (sh "firefox" "http:/127.0.0.1:9897")
  (stop-khala))

(defn mount-pensieve []
  (mount/pensieve "pensieve" (u/expand-home "$HOME/pensieve")))

(defn unmount-pensieve []
  (sh "umount" (u/expand-home  "$HOME/pensieve")))
