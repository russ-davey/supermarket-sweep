(ns supermarket-sweep.test-totals
  (:require [clojure.test :refer :all]
            [supermarket-sweep.totals :as target]
            [supermarket-sweep.test-data :as test-data]))

(deftest total-price
  (testing "calculate the sub total"
    (let [results (target/calcuate-total
                    [(:price-per-unit test-data/beans)
                     (:price-per-unit test-data/coke-can)
                     (:price-per-unit test-data/onions)
                     (:price-per-unit test-data/beans)])]
      (is (= 4.33
            results)))))