(ns supermarket-sweep.core)

(defn apply-promotions
  [])

(defn calculate-price
  [shopping-list]
  )

(defmulti item->item+details
          "Given an item, look up the price and return a map that includes the price
          and any promotions.
          In production this would be a database query but for the purpose of this
          exercise the values and promotions will be hardcoded"
          :item)

(defmethod item->item+details "beans-tin"
  [{:keys [item]}]
  {:item "beans-tin"
   :name "Beans"
   :price-per-unit 1.75
   :promo-code "bogof-beans"
   :promotion-name "Beans 3 for 2"
   :buy 3
   :free 2})

; At checkout the weight is input in kg when the item is scanned
(defmethod item->item+details "onion"
  [{:keys [item weight]}]
  {:item item
   ;:name "Onions"
   :name (str "Onions " weight
              " kg @ 0.29/kg")
   :price-per-kg 0.29
   :weight weight})

(defmethod item->item+details "coke-can"
  [{:keys [item]}]
  {:item item
   :name "Coke"
   :price-per-unit 0.70
   :promo-code "super-3"})

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
  {:item item
   :name "Brown Ale"
   :price-per-unit 2.25
   :promo-code "super-4"
   :promotion-name "3 ales for £6"})

(defmethod item->item+details "cask-ale-bottle"
  [{:keys [item]}]
  {:item item
   :name "Cask Ale"
   :price-per-unit 2.57
   :promo-code "super-4"
   :promotion-name "3 ales for £6"})

(defmethod item->item+details "pale-ale-bottle"
  [{:keys [item]}]
  {:item item
   :name "Pale Ale"
   :price-per-unit 2.99
   :promo-code "super-4"
   :promotion-name "3 ales for £6"})

(defmethod item->item+details :default
  [{:keys [item]}]
  (str "Unknown item: " item))

(defn shopping-list->shopping-list+details
  "Given a vector of items look up item details and return a map"
  [shopping-list]
  (->> shopping-list
       (map #(item->item+details %))))
