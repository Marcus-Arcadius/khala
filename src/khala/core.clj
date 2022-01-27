(ns khala.core
  (:require [org.httpkit.server :refer [run-server]]
            [clj-time.core :as t]
            [compojure.core :refer :all]
            [compojure.route :as route])
  (:gen-class))

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str (t/time-now))})

(defroutes app
  (GET "/" [] "<h1>Khala</h1>")
  (GET "/prompt" [] (get-time))
  (route/not-found "<h1>Khala service not found</h1>"))

(defn -main [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "9837"))] ;(5) 
    (server/run-server app {:port port})
    (println (str "Running Khala at http:/127.0.0.1:" port "/")))

  (println "Server started on port 8080"))