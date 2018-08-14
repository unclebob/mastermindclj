(ns mastermind.code-maker)

(defn position-matches [code guess]
  (count
    (filter identity
            (map #(= %1 %2) code guess))))

(defn score [code guess]
  [(position-matches code guess)
   0]
  )