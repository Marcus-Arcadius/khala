(ns khala.core-test
  (:require [clojure.test :refer :all]
            [khala.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(deftest khala-start-test
  (let [khala-port 9897]
    ;; This isn't asserting anything yet
    (start-khala khala-port)
    (is (= 1 1))))
