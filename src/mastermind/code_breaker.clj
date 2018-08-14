(ns mastermind.code-breaker)

(defn guess-to-number [guess]
  (reduce #(+ (* 6 %1) %2) guess))

(defn number-to-guess [n]
  [(rem (quot n 216) 6)
   (rem (quot n 36) 6)
   (rem (quot n 6) 6)
   (rem n 6)])

(defn inc-guess [guess]
  (let [guess-number (guess-to-number guess)]))

(defn break-code [past-guesses]
  [0 0 0 0])
