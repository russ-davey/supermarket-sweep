(ns supermarket-sweep.core)

; List of promotions
(def bogof-beans
  {:promo-code "bogof-beans"
   :promotion-name "Beans 3 for 2"
   :buy 3
   :free 2})

(def two-for-one-pound-coke
  {:promo-code "two-for-one-pound-coke"
   :promotion-name "Coke 2 for £1"
   :promo-price 1.00
   :buy 2
   :free 1})

(def cure-for-what-ales-you
  {:promo-code "cure-for-what-ales-you"
   :promotion-name "3 ales for £6"
   :promo-price 6.00
   :buy 3})

(defn format-double
  [double]
  (Double/parseDouble
    (format "%.2f" double)))

(defmulti item->item+details
          "Given an item, look up the price and return a map that includes the price
          and any promotions.
          In production this would be a database query but for the purpose of this
          exercise the values and promotions will be hardcoded"
          :item)

(defmethod item->item+details "beans-tin"
  [{:keys [item]}]
  (conj {:item item
         :name "Beans"
         :price-per-unit 1.75}
        bogof-beans))

; At checkout the weight is input in kg when the item is scanned
(defmethod item->item+details "onion"
  [{:keys [item weight]}]
  (if (nil? weight)
    ; raise exception here if weight hasn't been entered
    (println "Please enter weight")
    {:item item
     :name (str "Onions " weight
                " kg @ £0.29/kg")
     :price-per-kg 0.29
     :weight weight
     :price-per-unit (format-double (* 0.29 weight))}))

(defmethod item->item+details "coke-can"
  [{:keys [item]}]
  (conj {:item item
         :name "Coke"
         :price-per-unit 0.70}
        two-for-one-pound-coke))

(defmethod item->item+details "large-oranges"
  [{:keys [item]}]
  {:item item
   :name "Oranges"
   :price-per-unit 0.73})

(defmethod item->item+details "odd-ale-bottle"
  [{:keys [item]}]
  {:item item
   :name "Odd Ale"
   :price-per-unit 5.25})

(defmethod item->item+details "brown-ale-bottle"
  [{:keys [item]}]
  (conj {:item item
         :name "Brown Ale"
         :price-per-unit 2.25}
        cure-for-what-ales-you))

(defmethod item->item+details "cask-ale-bottle"
  [{:keys [item]}]
  (conj {:item item
         :name "Cask Ale"
         :price-per-unit 2.57}
        cure-for-what-ales-you))

(defmethod item->item+details "pale-ale-bottle"
  [{:keys [item]}]
  (conj {:item item
         :name "Pale Ale"
         :price-per-unit 2.99}
        cure-for-what-ales-you))

(defmethod item->item+details :default
  [{:keys [item]}]
  (str "Unknown item: " item))

(defn shopping-list->shopping-list+details
  "Given a vector of items look up item details and return a map"
  [shopping-list]
  (->> shopping-list
       (map #(item->item+details %))))
