(ns mastermind.code-breaker
  (:require [mastermind.code-maker :as cm]))

(defn guess-to-number [guess]
  (reduce #(+ (* 6 %1) %2) guess))

(defn number-to-guess [n]
  [(rem (quot n 216) 6)
   (rem (quot n 36) 6)
   (rem (quot n 6) 6)
   (rem n 6)])

(defn inc-guess [guess]
  (if (= guess [5 5 5 5])
    :overflow
    (->> guess
         (guess-to-number)
         (inc)
         (number-to-guess))))

(defn guess-consistent-with-past-scores [guess past-scores]
  (every? identity (for [past-score past-scores]
                     (= (cm/score guess (first past-score))
                        (second past-score)))))

(defn next-guess [starting-guess past-scores]
  (loop [guess starting-guess]
    (if (= guess :overflow)
      :error
      (if (guess-consistent-with-past-scores guess past-scores)
        guess
        (recur (inc-guess guess))))))


(defn break-code-seq
  "Sequential Strategy"
  [starting-guess past-scores]
  (if (nil? starting-guess)
    [0 0 0 0]
    (next-guess starting-guess past-scores)))

(defn break-code-3x2
  "The 3x2 strategy"
  [starting-guess past-scores]
  (case (count past-scores)
    0 [0 0 1 1]
    1 [2 2 3 3]
    2 [4 4 5 5]
    3 (next-guess [0 0 0 0] past-scores)
    (next-guess starting-guess past-scores)))

(defn break-code-double-rainbow
  "All the way!"
  [starting-guess past-scores]
  (case (count past-scores)
    0 [0 1 2 3]
    1 [2 3 4 5]
    2 [4 5 0 1]
    3 (next-guess [0 0 0 0] past-scores)
    (next-guess starting-guess past-scores)))
