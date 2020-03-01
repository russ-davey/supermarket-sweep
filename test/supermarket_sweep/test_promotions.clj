(ns supermarket-sweep.test-promotions
  (:require [clojure.test :refer :all]
            [supermarket-sweep.promotions :as target]
            [supermarket-sweep.test-data :as test-data]))

(deftest apply-bogof-promotion
  (testing "bogof 1 beans"
    (let [results (target/shopping-list+details->discounts
                    [test-data/beans])]
      (is (nil?
            results))))
  (testing "bogof 3 beans"
    (let [results (target/shopping-list+details->discounts
                    [test-data/beans
                     test-data/beans
                     test-data/beans])]
      (is (=  {:name "Beans 3 for 2" :discount -1.75}
             results))))
  (testing "bogof 4 beans"
    (let [results (target/shopping-list+details->discounts
                    [test-data/beans
                     test-data/beans
                     test-data/beans
                     test-data/beans])]
      (is (= {:name "Beans 3 for 2" :discount -1.75}
             results))))
  (testing "bogof 6 beans"
    (let [results (target/shopping-list+details->discounts
                    [test-data/beans
                     test-data/beans
                     test-data/beans
                     test-data/beans
                     test-data/beans
                     test-data/beans])]
      (is (= {:name "Beans 3 for 2" :discount -3.50}
             results)))))