(ns supermarket-sweep.totals)

(defn sub-total
  [shopping-list]
  (Double/parseDouble
    (format "%.2f"
            (reduce (fn [total {:keys [price-per-unit price-per-weight weight]}]
                      (if price-per-unit
                        (+ total price-per-unit)
                        (+ total (* price-per-weight weight))))
                    0
                    shopping-list))))