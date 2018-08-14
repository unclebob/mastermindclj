(ns mastermind.code-maker)

(defn score [code guess]
  (if (= (first code) (first guess))
    [:pos]
    []))