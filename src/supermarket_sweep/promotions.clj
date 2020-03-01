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

(defn x-for-y-pounds
  "calculate the discount when buying multiple items for a set price
  this is a terrible way to calculate it, fix later"
  [buy promo-price quantity price-per-unit]
  (let [cost-of-packs
        (-> (int (/ quantity buy))
            (+ (mod quantity buy))
            (- quantity)
            (* promo-price))
        extras (* (mod quantity buy) price-per-unit)
        total-cost-with (- (- cost-of-packs extras))
        total-cost-without (* quantity price-per-unit)]
    ;(println "extras" extras)
    ;(println "total cost with discount:" total-cost-with)
    ;(println "total cost without discount" total-cost-without)
    ;(println "discount:" (- total-cost-without total-cost-with))
    (Double/parseDouble
      (format "%.2f" (- (- total-cost-without total-cost-with))))))

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
  (let [first-item (first (second items))
        discount (x-for-y-pounds (:buy first-item)
                                 (:promo-price first-item)
                                 (count (second items))
                                 (:price-per-unit first-item))]
    (if (< discount 0)
      (conj acc
            {:name (:promotion-name first-item)
             :discount discount}))))

(defmethod items->discount "cure-for-what-ales-you-test"
  [acc items]
  (let [first-item (first (second items))
        discount (x-for-y-pounds (:buy first-item)
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