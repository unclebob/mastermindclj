(ns mastermind.code-maker)

(defn count-true [bools]
  (count (filter identity bools)))

(defn position-matches [code guess]
  (count-true
    (map #(= %1 %2) code guess)))

(defn value-matches [code guess]
  (count-true
    (map #(contains? (set code) %1) guess)))

(defn count-of [value values]
  (count (filter #(= value %) values)))

(defn over-count [code guess]
  (let [code-values (set code)]
    (reduce +
            (filter pos?
                    (map #(- (count-of % guess) (count-of % code))
                         code-values)))
    ))

(defn score [code guess]
  (let [p (position-matches code guess)
        v (value-matches code guess)
        o (over-count code guess)]
    [p (- v p o)])
  )