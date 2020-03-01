(ns supermarket-sweep.core-test
  (:require [clojure.test :refer :all]
            [supermarket-sweep.core :as target]
            [supermarket-sweep.test-data :as test-data]))

(deftest shopping-list->shopping-list+details
  (testing "prices are added to the shopping list"
    (let [results (target/shopping-list->shopping-list+details
                    test-data/shopping-list-small)]
      (is (= [{:buy 3
               :free 2
               :item "beans-tin"
               :name "Beans"
               :price-per-unit 1.75
               :promo-code "bogof-beans"
               :promotion-name "Beans 3 for 2"}
              {:item "onion"
               :name "Onions  kg @ 0.29/kg"
               :price-per-kg 0.29
               :weight nil}
              {:item "coke-can"
               :name "Coke"
               :price-per-unit 0.7
               :promo-code "super-3"}
              {:item "brown-ale-bottle"
               :name "Brown Ale"
               :price-per-unit 2.25
               :promo-code "super-4"
               :promotion-name "3 ales for £6"}
              {:item "onion"
               :name "Onions 500 kg @ 0.29/kg"
               :price-per-kg 0.29
               :weight 500}]
             results)))))




