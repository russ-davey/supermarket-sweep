(ns supermarket-sweep.totals)

(defn calcuate-total
  "calculate the total price from a vector of numbers and return a double"
  [number-vec]
  (Double/parseDouble
    (format "%.2f"
            (reduce (fn [total price]
                      (if (not (nil? price))
                        (+ total price)
                        total))
                    0
                    number-vec))))