;; Rename the project to pen, and have khala as a subproject
(ns khala.core
  (:require [clojure.string :as str]
            [khala.khala :as khala]
            [khala.pensieve :as pensieve]
            [khala.utils :as utils])
  (:gen-class))

(defn start-khala [port]
  (khala/start port))

(defn -main [& args]
  ;; "Immediately start Khala. It's through Khala that the other systems can be controlled."
  (start-khala (Integer/parseInt (or (System/getenv "PORT") "9837"))))
