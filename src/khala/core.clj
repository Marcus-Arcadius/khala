;; Rename the project to pen, and have khala as a subproject
(ns khala.core
  (:require [clojure.string :as str]
            [khala.khala :as khala]
            [khala.pensieve :as pensieve]
            [khala.utils :as utils])
  (:gen-class))

(defn -main [& args]
  (let [khala-port (Integer/parseInt (or (System/getenv "PORT") "9837"))] ;(5)

    ;; Immediately start Khala. It's through Khala that the other systems can be controlled.
    (khala/start khala-port)))
