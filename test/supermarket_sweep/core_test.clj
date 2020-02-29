(ns supermarket-sweep.core-test
  (:require [clojure.test :refer :all]
            [supermarket-sweep.core :as target]
            [supermarket-sweep.test-data :as test-data]))

(deftest shopping-list->shopping-list+details
  (testing "prices are added to the shopping list"
    (let [results (target/shopping-list->shopping-list+details
                    test-data/shopping-list-small)]
      (is (= [{:item "beans-tin"
               :name "Beans"
               :price-per-unit 1.75
               :promo-code "super-1"}
              {:item "onion"
               :name "Onions"
               :price-per-kg 0.29
               :promo-code "super-2"}
              {:item "coke-can"
               :name "Coke"
               :price-per-unit 0.7
               :promo-code "super-3"}
              {:item "brown-ale-bottle"
               :name "Brown Ale"
               :price-per-unit 2.25
               :promo-code "super-4"}]
             results)))))





