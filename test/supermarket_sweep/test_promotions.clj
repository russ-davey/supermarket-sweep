(ns supermarket-sweep.test-promotions
  (:require [clojure.test :refer :all]
            [supermarket-sweep.promotions :as target]
            [supermarket-sweep.test-data :as test-data]))

(deftest bogof-promotion
  (testing "bogof 1 beans"
    (let [results (target/shopping-list+details->discounts
                    [test-data/beans
                     test-data/oranges])]
      (is (nil?
            results))))
  (testing "bogof 3 beans"
    (let [results (target/shopping-list+details->discounts
                    [test-data/beans
                     test-data/beans
                     test-data/beans
                     test-data/oranges])]
      (is (=  {:name "Beans 3 for 2" :discount -1.75}
             results))))
  (testing "bogof 4 beans"
    (let [results (target/shopping-list+details->discounts
                    [test-data/beans
                     test-data/beans
                     test-data/beans
                     test-data/beans
                     test-data/oranges])]
      (is (= {:name "Beans 3 for 2" :discount -1.75}
             results))))
  (testing "bogof 6 beans"
    (let [results (target/shopping-list+details->discounts
                    [test-data/beans
                     test-data/beans
                     test-data/beans
                     test-data/beans
                     test-data/beans
                     test-data/beans
                     test-data/oranges])]
      (is (= {:name "Beans 3 for 2" :discount -3.50}
             results)))))

(deftest two-cans-for-1-pound-promotion
  (testing "1 can"
    (let [results (target/shopping-list+details->discounts
                    [test-data/coke-can])]
      (is (nil?
            results))))
  (testing "2 cans"
    (let [results (target/shopping-list+details->discounts
                    [test-data/coke-can
                     test-data/coke-can])]
      (is (= {:name "Coke 2 for £1" :discount -0.40}
           results))))
  (testing "3 cans"
    (let [results (target/shopping-list+details->discounts
                    [test-data/coke-can
                     test-data/coke-can
                     test-data/coke-can])]
      (is (= {:name "Coke 2 for £1" :discount -0.40}
             results))))
  (testing "4 cans"
    (let [results (target/shopping-list+details->discounts
                    [test-data/coke-can
                     test-data/coke-can
                     test-data/coke-can
                     test-data/coke-can])]
      (is (= {:name "Coke 2 for £1" :discount -0.80}
             results)))))