(ns khala.core
  (:require [org.httpkit.server :refer [run-server] :as server]
            [clj-time.core :as t]
            [cheshire.core :as c]
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

;; (defn app [req]
;;   {:status  200
;;    :headers {"Content-Type" "text/html"}
;;    :body    (str (t/time-now))})

(defn printPostBody [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body request})

;; Post would contain payloads
;; (defroutes routes
;;   (POST "/login" request (printPostBody request))
;;   (route/not-found {:status 404 :body "<h1>Page not found</h1"}))

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

;; https://www.baeldung.com/clojure-ring
;; (app {:uri "/prompt" :request-method :post :headers {"Content-Type" "application/json"} :body "{\"fun\": \"pf-tweet-sentiment/1\", \"args\": \"I love chocolate\"}"})
;; (app {:uri "/prompt" :request-method :post :headers {"content-type" "application/json" "content-length" "59"} :body "{\"fun\": \"pf-tweet-sentiment/1\", \"args\": \"I love chocolate\"}"})
(defroutes app
  (GET "/" [] "<h1>Khala</h1>")
  ;; (GET "/" [] (fn [req] "Do something with req"))
  ;; curl -d "fun=pf-tweet-sentiment%2F1&args=%5B%22I%20love%20chocolate%22%5D" -X POST http://127.0.0.1:9837/prompt -H "Content-Type: application/x-www-form-urlencoded"
  ;; curl -d "{\"fun\": \"pf-tweet-sentiment/1\", \"args\": \"I love chocolate\"}" -X POST http://127.0.0.1:9837/prompt -H "Content-Type: application/json"
  (POST "/prompt" req
        (let [fun (get (:params req) :fun)
              ;; json
              args (get (:params req) :args)]
          (sh "tv" :stdin (str req))
          ;; (c/parse-string
          ;;  (apply
          ;;   penf (conj (c/parse-string args true) fun))
          ;;  true)
          ))
  ;; Use urlencode
  ;; curl "http://127.0.0.1:9837/post/prompt/pf-tweet-sentiment%2F1/%5B%22I%20love%20chocolate%22%5D"
  (GET "/prompt/:fun/:args" [fun,args]
       (c/parse-string
        (apply
         penf (conj (c/parse-string args true) fun))
        true))
  ;; (GET "/gettime" [] (get-time))
  ;; (GET "/get/prompt" req (prompt req))
  (GET "/hello/:name" [name] (str "Hello " name))
  ;; You can adjust what each parameter matches by supplying a regex:
  (GET ["/file/:name.:ext" :name #".*", :ext #".*"] [name ext]
       (str "File: " name ext))
  (route/not-found "<h1>Khala service not found</h1>"))

;; (app {:uri "/" :request-method :post})

;; (defroutes app
;;   (GET "/" [] "Show something")
;;   (POST "/" [] "Create something")
;;   (PUT "/" [] "Replace something")
;;   (PATCH "/" [] "Modify Something")
;;   (DELETE "/" [] "Annihilate something")
;;   (OPTIONS "/" [] "Appease something")
;;   (HEAD "/" [] "Preview something"))

(defn -main [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "9837"))] ;(5)
    (server/run-server app {:port port})
    (println (str "Running Khala at http:/127.0.0.1:" port "/"))))