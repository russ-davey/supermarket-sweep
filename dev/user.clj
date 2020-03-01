(ns user
  (:require [supermarket-sweep.core :refer :all]
            [supermarket-sweep.promotions :refer :all]
            [supermarket-sweep.receipt :refer :all]
            [supermarket-sweep.totals :refer :all]
            [clojure.string :as string]))

(def shopping-list [{:item "beans-tin"}
                    {:item "beans-tin"}
                    {:item "beans-tin"}
                    {:item "beans-tin"}
                    {:item "onion" :weight 0.452}
                    {:item "coke-can"}
                    {:item "coke-can"}
                    {:item "brown-ale-bottle"}
                    {:item "cask-ale-bottle"}
                    {:item "pale-ale-bottle"}])

(defn print-receipt
  []
  (let [shopping-items (shopping-list->shopping-list+details shopping-list)
        sub-total (calcuate-total (map :price-per-unit shopping-items))
        discounts (shopping-list+details->discounts shopping-items)
        total-savings (calcuate-total (map :discount discounts))]
    (conj ["Thanks for shopping at Supermarket Sweep"]
          (string/join (repeat 40 "-"))
          (map (juxt :name :price-per-unit) shopping-items)
          (string/join (repeat 40 "-"))
          ["Sub total" sub-total]
          (string/join (repeat 40 "-"))
          "Savings"
          (map (juxt :name :discount) discounts)
          (string/join (repeat 40 "-"))
          ["Total savings" total-savings]
          (string/join (repeat 40 "-"))
          ["Total to pay" (+ sub-total total-savings)]
          (string/join (repeat 40 "-"))
          "Thank you, come again!")))

