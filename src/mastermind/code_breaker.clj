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
  (->> guess
       (guess-to-number)
       (inc)
       (number-to-guess)))

(defn next-guess [last-guess past-guesses]
  (loop [guess (inc-guess last-guess)]
    (if (= guess [0 0 0 0])
      :error
      (if (every? identity (for [past-guess past-guesses]
                             (= (cm/score guess (first past-guess))
                                (second past-guess))))
        guess
        (recur (inc-guess guess))))))

  (defn break-code [last-guess past-guesses]
    (if (nil? last-guess)
      [0 0 0 0]
      (next-guess last-guess past-guesses)))
