;; this was pensieve.core
(ns khala.mount
  (:require
   [clojure.repl :refer :all]
   [khala.rc :as rc]
   [khala.fuse-pensieve :as fpensieve]
   [khala.utils :as u]
   [clojure.core.async
    :as a
    :refer [>! <! >!! <!! go chan buffer close! thread
            alts! alts!! take! put! timeout]])
  (:gen-class))

(use '[clojure.java.shell :only [sh]])
(use '[clojure.string :only (join split upper-case)])

(defn pensieve [type dir]
      ;; Wrapping into a shell command is unneccessary with sh
    ;; (sh (u/cmd "mkdir" "-p" dir))
  (sh "mkdir" "-p" dir)
  ;; (sh "sh" "-c" (str (u/cmd "u/cmd" "mkdir" "-p" dir) " | pen-tv"))
  (cond
    ;; Make it so when a path that doesn't exist is read, it is created

    ;; Make multiple modes. For example:
    ;; - a computer's filesystem
    ;; - chatbot memories

    ;; - loom mode

    ;; This mode is called 'pensieve'.
    ;; It's the prototype, and will simply imagine a filesystem.
    (= "pensieve" type) (fpensieve/mount-pensieve dir)
    :else (println "Please use a known system as first arg [pensieve]")))

(defn pensieve-test-list-existing-dirs []
  @khala.pensieve/directories-atom)

(defn pensieve-test-gen-list-files-of-existing-dir []
  (khala.pensieve/get-pensieve-filenames (nth @khala.pensieve/directories-atom 2)))
