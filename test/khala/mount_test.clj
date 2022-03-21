(ns khala.mount-test
  (:require [khala.mount :as sut]
            [clojure.test :as t]

            [khala.utils :as utils]
            [clojure.core.async
             :as a
             :refer [>! <! >!! <!! go chan buffer close! thread
                     alts! alts!! take! put! timeout]]))

(def simplechan (chan))

(deftest pensieve-test
  (testing "pensieve mounts"
    (put! simplechan (sut/pensieve "pensieve" (utils/expand-home "$HOME/pensieve")))
    (take! simplechan println)
    (is (= 0 1))))
