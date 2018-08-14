(ns mastermind.code-maker)

(defn position-matches [code guess]
  (count
    (filter identity
            (map #(= %1 %2) code guess))))

(defn value-matches [code guess]
  (count
    (filter identity
            (map #(contains? (set code) %1) guess))))

(defn score [code guess]
  (let [p (position-matches code guess)
        v (value-matches code guess)]
    [p (- v p)])
  )