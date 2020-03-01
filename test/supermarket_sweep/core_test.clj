(ns supermarket-sweep.core-test
  (:require [clojure.test :refer :all]
            [supermarket-sweep.core :as target]
            [supermarket-sweep.test-data :as test-data]))

(deftest shopping-list->shopping-list+details
  (testing "prices are added to the shopping list, contains deliberate mistake
   of missing weight for onion which returns a nil"
    (let [results (target/shopping-list->shopping-list+details
                    test-data/shopping-list-small)]
      (println results)
      (is (= [{:buy 3
               :free 2
               :item "beans-tin"
               :name "Beans"
               :price-per-unit 1.75
               :promo-code "bogof-beans"
               :promotion-name "Beans 3 for 2"}
              nil
              {:buy 2
               :item "coke-can"
               :name "Coke"
               :price-per-unit 0.7
               :promo-code "two-for-one-pound-coke"
               :promo-price 1.0
               :promotion-name "Coke 2 for £1"}
              {:buy 3
               :item "brown-ale-bottle"
               :name "Brown Ale"
               :price-per-unit 2.25
               :promo-code "cure-for-what-ales-you"
               :promo-price 6.0
               :promotion-name "3 ales for £6"}
              {:item "onion"
               :name "Onions 0.535 kg @ £0.29/kg"
               :price-per-kg 0.29
               :price-per-unit 0.16
               :weight 0.535}]
             results)))))




