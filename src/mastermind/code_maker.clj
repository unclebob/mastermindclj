(ns mastermind.code-maker)

(defn score [code guess]
  (filter some? (map #(if (= %1 %2) :pos) code guess)))