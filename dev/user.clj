(ns user
  (:require [supermarket-sweep.core :refer :all]
            [supermarket-sweep.promotions :refer :all]
            [supermarket-sweep.receipt :refer :all]))

(def shopping-list [{:item "beans-tin"}
                    {:item "beans-tin"}
                    {:item "beans-tin"}
                    {:item "onion"}
                    {:item "coke-can"}
                    {:item "coke-can"}
                    {:item "brown-ale-bottle"}
                    {:item "cask-ale-bottle"}
                    {:item "pale-ale-bottle"}])

(defn print-receipt
  []
  (let [main (shopping-list->shopping-list+details shopping-list)
        discounts (shopping-list+details->discounts main)
        ]
    (println main)
    (println discounts)
    ))

