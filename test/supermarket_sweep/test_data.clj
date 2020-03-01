(ns supermarket-sweep.test-data
  (:require [clojure.test :refer :all]))

(def shopping-list-small [{:item "beans-tin"}
                          {:item "onion"}
                          {:item "coke-can"}
                          {:item "brown-ale-bottle"}
                          {:item "onion" :weight 0.535}])

(def shopping-list [{:item "beans-tin"}
                    {:item "beans-tin"}
                    {:item "beans-tin"}
                    {:item "onion" :weight 0.535}
                    {:item "coke-can"}
                    {:item "coke-can"}
                    {:item "brown-ale-bottle"}
                    {:item "cask-ale-bottle"}
                    {:item "pale-ale-bottle"}])

(def beans
  {:item "beans-tin"
   :name "Beans"
   :price-per-unit 1.75
   :promo-code "bogof-beans"
   :promotion-name "Beans 3 for 2"
   :buy 3
   :free 2})

(def coke-can
  {:item "coke-can"
   :name "Coke"
   :price-per-unit 0.70
   :promo-code "two-for-one-pound-coke"
   :promotion-name "Coke 2 for £1"
   :promo-price 1.00
   :buy 2})

(def onions
  {:item "onion"
   :name "Onions"
   :price-per-weight 0.29
   :weight 0.435
   :price-per-unit 0.13})

(def oranges
  {:item "oranges"
   :price-per-unit 0.73})
