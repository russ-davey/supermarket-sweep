(ns supermarket-sweep.test-data
  (:require [clojure.test :refer :all]))

(def shopping-list-small [{:item "beans-tin"}
                    {:item "onion"}
                    {:item "coke-can"}
                    {:item "brown-ale-bottle"}])

(def shopping-list [{:item "beans-tin"}
                    {:item "beans-tin"}
                    {:item "beans-tin"}
                    {:item "onion"}
                    {:item "coke-can"}
                    {:item "coke-can"}
                    {:item "brown-ale-bottle"}
                    {:item "cask-ale-bottle"}
                    {:item "pale-ale-bottle"}])

(def beans
  {:item "beans-tin" :name "Beans" :price-per-unit 1.75 :promo-code "bogof-beans" :parameter1 3 :parameter2 2})

(def oranges
  {:item "oranges" :price-per-unit 0.73})
