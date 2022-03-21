(ns khala.core-test
  (:require [clojure.test :refer :all]
            [khala.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))

(deftest a-works- test
  (testing "FIXME, I fail."
    (is (= 0 0))))

;; Test the full life-cycle
(deftest khala-start-test
  (testing "khala starts and is available"
    (let [khala-port 9897]
      ;; This isn't asserting anything yet
      (start-khala khala-port)
      (testing "khala available"
        (is (= 0 1)))
      (is (= 1 1))
      (stop-khala)
      (is (= 0 1)))))
