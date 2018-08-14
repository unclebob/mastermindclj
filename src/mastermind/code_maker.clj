(ns mastermind.code-maker)

(defn count-true [bools]
  (count (filter identity bools)))

(defn position-matches [code guess]
  (count-true
    (map #(= %1 %2) code guess)))

(defn value-matches [code guess]
  (count-true
    (map #(contains? (set code) %1) guess)))

(defn score [code guess]
  (let [p (position-matches code guess)
        v (value-matches code guess)]
    [p (- v p)])
  )