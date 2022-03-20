(ns khala.utils
  (:require
   [clojure.string :as str]
   [clojure.repl :refer :all])
  (:import
   (java.io File)
   (java.nio.file Paths)))

(use '[clojure.java.shell :only [sh]])
(use '[clojure.string :only (join split upper-case)])

(defn cmd
  ""
  [& args]
  (clojure.string/join
   " "
   ;; I have to use the jq version so unicode works
   ;; But it's much slower. So I have to rewrite this with clojure
   (map (fn [s] (->
                 (sh "pen-q-jq" :in (str s))
                 :out)) args)))

;; (defn app [req]
;;   {:status  200
;;    :headers {"Content-Type" "text/html"}
;;    :body    (str (t/time-now))})

(defn tv [s]
  (sh "pen-tv" :in (str s))
  s)

(defn args-to-envs [args]
  (join "\n"
        (map (fn [[key value]]
               (str
                (str/replace
                 (upper-case
                  (name key))
                 "-" "_")
                "=" (cmd value)))
             (seq args))))

(defn string-to-uri [s]
  (-> s File. .toURI))

(defn uri-to-path [s]
  (Paths/get s))

(defn string-to-path [s]
  (-> s string-to-uri uri-to-path))

(defn split-by-slash [s]
  (clojure.string/split s #"/"))

(defn member [s col]
  (some #(= s %) col))

(defmacro lexical-ctx-map
  "Pull in all the lexical bindings into a map for passing somewhere else."
  []
  (let [symbols (keys &env)]
    (zipmap (map (fn [sym] `(quote ~(keyword sym)))
                 symbols)
            symbols)))
