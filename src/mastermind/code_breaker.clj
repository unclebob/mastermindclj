(ns mastermind.code-breaker)

(defn guess-to-number [guess]
  (reduce #(+ (* 6 %1) %2) guess))

(defn inc-guess [guess]
  (let [guess-number (guess-to-number guess)]))

(defn break-code [past-guesses]
  [0 0 0 0])
