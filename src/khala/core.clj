(ns khala.core
  (:require [org.httpkit.server :refer [run-server]]
            [clj-time.core :as t]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.core.async :as a])
  (:gen-class))

(use '[clojure.java.shell :only [sh]])

(defn cmd
  ""
  [& args]
  (clojure.string/join
   " "
   (map (fn [s] (->
                 (sh "q" :in (str s))
                 :out)) args)))

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str (t/time-now))})

(defn printPostBody [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body request})

;; Post would contain payloads
(defroutes routes
  (POST "/login" request (printPostBody request))
  (route/not-found {:status 404 :body "<h1>Page not found</h1"}))

(defroutes app
  (GET "/" [] "<h1>Khala</h1>")
  (GET "/gettime" [] (get-time))
  (GET "/prompt" req (prompt req))
  (route/not-found "<h1>Khala service not found</h1>"))

(defn -main [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "9837"))] ;(5) 
    (server/run-server app {:port port})
    (println (str "Running Khala at http:/127.0.0.1:" port "/")))

  (println "Server started on port 8080"))