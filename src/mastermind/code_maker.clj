(ns mastermind.code-maker)

(defn score [code guess]
  [(count (filter identity (map #(= %1 %2) code guess)))]
  )