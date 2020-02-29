(ns supermarket-sweep.core-test
  (:require [clojure.test :refer :all]
            [supermarket-sweep.core :as target]))

(deftest shopping-list->shopping-list+prices
  (testing "prices are added to the shopping list"
    (let [results (target/shopping-list->shopping-list+prices ["beans-tin"])]
      (println results))))
