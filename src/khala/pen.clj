(ns khala.pen
  (:require [cheshire.core :as c]))

(use '[clojure.java.shell :only [sh]])
(use '[khala.utils :only (cmd tv args-to-envs)])

;; pen.el interop

;; The proxy system must be able to send back all results,
;; Not in the format of a list of directories.
;; Rather a singular json containing all results, which are reconstructed as directories

(defn debug-lm-complete []
  (->
   (sh "pen-test-proxy-lm-complete")
   :out))

(defn lm-complete [request]
  (let* [envs-map (:body request)]
    (->
     (sh "lm-complete" :in (str
                            (args-to-envs
                             (assoc
                              envs-map
                              :PEN_PROXY_RESPONSE "y"))))
     :out)))

(defn penf [& args]
  ;; This is how to run a macro at runtime
  (eval
   `(-> (sh "unbuffer" "penf" "-u" "-nto" "--pool" "-j"
            ~@args)
        :out)))

(defn pena [& args]
  ;; This is how to run a macro at runtime
  (eval
   `(-> (sh "unbuffer" "pena" "-u" "-nto" "--pool" "-j"
            ~@args)
        :out)))

(defn prompt [request]
  (let* [b (:body request)
         fun (:fun b)
         args (:args b)]
    (c/parse-string
     (apply
      penf (conj (c/parse-string args true) fun))
     true)))





(comment
  (let [args '("pf-tweet-sentiment/1" "I love pizza")]
    (eval
     `(penf fun ~@(c/parse-string args true))))

  (let [fun "pf-tweet-sentiment/1"
        args "[\"I love pizza\"]"]
    (c/parse-string
     (apply
      penf (conj (c/parse-string args true) fun))
     true)))
