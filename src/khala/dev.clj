(ns khala.dev
  (:require [khala.core :refer :all]
            [khala.utils :as u]
            [khala.mount :as mount]))

(use '[clojure.java.shell :only [sh]])

(defn open-khala []
  (start-khala 9897)
  (sh "firefox" "http:/127.0.0.1:9897")
  (stop-khala))

(defonce mnt (atom nil))

;; This has to be able to mount asynchronously
(defn dev-mount-pensieve []
  ;; When it mounts, the REPL becomes locked.
  ;; I have to find a way to run it asynchronously
  (swap! mnt
         (mount/pensieve "pensieve" (u/expand-home "$HOME/pensieve"))))

(defn unmount-pensieve []
  (sh "pen-sps" "zrepl" "-cm" "umount" (u/expand-home  "$HOME/pensieve"))
  ;; (sh "umount" "-l" (u/expand-home  "$HOME/pensieve"))
  )

(defn pen-test-interactive-clj [a b c]
  (sh "pen-tv" :in a))
