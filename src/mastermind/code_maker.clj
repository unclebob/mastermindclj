(ns mastermind.code-maker)

(defn score [code guess]
  ;[(count (filter some? (map #(if (= %1 %2) :pos) code guess)))]
  [(reduce + (map #(if (= (first %) (second %)) 1 0) (partition 2 (interleave code guess))))]

  )