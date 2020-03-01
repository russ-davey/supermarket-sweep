(ns supermarket-sweep.promotions)

(defmulti items->discount
          "Given an item, look up the promotion code and apply discount"
          (fn [_ items]
            (first items)))

; TODO: refactor free name to for
(defn bogof
  "calculate the bogof discount for a group of items"
  [buy free quantity price-per-unit]
  (-> (int (/ quantity buy))
      (* free)
      (+ (mod quantity buy))
      (- quantity)
      (* price-per-unit)))

(defn x-for-y
  "calculate the discount when buying multiple items for a set price"
  [buy free promo-price quantity price-per-unit]
  (-> (int (/ quantity buy))
      (* free)
      (+ (mod quantity buy))
      (- quantity)
      ; what's left?
  ;(-> (int (/ quantity buy))
  ;    (* promo-price)
  ;    ; add the extra one here on
  ;    (+ (mod quantity buy))
      ))

(defmethod items->discount "bogof-beans"
  [acc items]
  (let [first-item (first (second items))
        discount (bogof (:buy first-item)
                        (:free first-item)
                        (count (second items))
                        (:price-per-unit first-item))]
    (if (< discount 0)
      (conj acc
            {:name (:promotion-name first-item)
             :discount discount}))))

(defmethod items->discount "two-for-one-pound-coke"
  [acc items]
  ;(clojure.pprint/pprint items)
  (let [first-item (first (second items))
        discount (x-for-y (:buy first-item)
                          (:free first-item)
                          (:promo-price first-item)
                          (count (second items))
                          (:price-per-unit first-item))]
    (print "discount:" discount)
    (if (< discount 0)
      (conj acc
            {:name (:promotion-name first-item)
             :discount discount}))))

(defmethod items->discount :default
  [acc _]
  acc)

(defn shopping-list+details->discounts
  [shopping-list]
  (->> (group-by :promo-code shopping-list)
       (reduce items->discount ())))