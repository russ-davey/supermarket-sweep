(ns supermarket-sweep.core)

(defmulti item->item+price
          "Given an item, look up the price and return a map that includes the price
          and any promotions.
          In production this would be a database query but for the purpose of this
          exercise the values and promotions will be hardcoded"
          :item)

(defn apply-promotions
  [])

(defmethod item->item+price "beans-tin"
  [{:keys [item]}]
  {:item item :price-per-unit 0.50})

(defmethod item->item+price "onion"
  [{:keys [item]}]
  {:item item :price-per-kg 0.29})

(defmethod item->item+price "coke"
  [{:keys [item]}]
  {:item item :price-per-unit 0.70})

(defmethod item->item+price "brown-ale-bottle"
  [{:keys [item]}]
  {:item item :price-per-unit 2.25})

(defmethod item->item+price "cask-ale-bottle"
  [{:keys [item]}]
  {:item item :price-per-unit 2.25})

(defmethod item->item+price "pale-ale-bottle"
  [{:keys [item]}]
  {:item item :price-per-unit 2.25})

(defmethod item->item+price :default
  [{:keys [item]}]
  (str "Unknown item: " item))

(defn shopping-list->shopping-list+prices
  [shopping-list]
  (->> shopping-list
      (map #(item->item+price %))))

(defn calculate-price
  [shopping-list]
  )
