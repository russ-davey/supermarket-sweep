(ns supermarket-sweep.test-totals
  (:require [clojure.test :refer :all]
            [supermarket-sweep.totals :as target]
            [supermarket-sweep.test-data :as test-data]))

(deftest total-price-by-quantity
  (testing "calculate the sub total"
    (let [results (target/sub-total
                    [test-data/beans
                     test-data/coke-can
                     test-data/onions
                     test-data/beans])]
      (is (= 4.33
            results)))))