(ns supermarket-sweep.promotions)

(defmulti items->discount
          "Given an item, look up the promotion code and apply discount"
          (fn [_ items]
            (first items)))
(defn bogof
  "calculate the bogof discount for a group of items"
  [for-units quantity price-per-unit]
  (-> (/ quantity for-units)
      (+ (mod quantity for-units))
      (- quantity)
      (* price-per-unit)))

(defmethod items->discount "bogof-beans"
  [_ items]
  (let [first-item (first (second items))
        name (:name first-item)
        parameter1 (:parameter1 first-item)
        parameter2 (:parameter2 first-item)
        price-per-unit (:price-per-unit first-item)]
    {:name (str name " " parameter1 " for " parameter2)
     :discount (bogof parameter2 (count items) price-per-unit)}))

(defmethod items->discount :default
  [accumulator _]
  accumulator)

(defn shopping-list+details->discounts
  [shopping-list]
  (->> (group-by :promo-code shopping-list)
       (reduce items->discount ())))