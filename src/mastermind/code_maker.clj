(ns mastermind.code-maker)

(defn score [code guess]
  [(count (filter some? (map #(if (= %1 %2) :pos) code guess)))])