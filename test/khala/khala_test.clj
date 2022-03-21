(ns khala.khala-test
  (:require [khala.khala :as khala]
            [clojure.test :as t]
            [clojure.spec.alpha :as s]))

(t/deftest khala-start-test
  (t/testing "khala starts and is available"
    (let [khala-port 9897]
      ;; This isn't asserting anything yet
      (t/is true (khala/start khala-port))
      (t/is true (khala/stop)))))
