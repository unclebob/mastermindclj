(ns mastermind.auto-play
  (:require
    [mastermind.code-breaker :as cb]
    [mastermind.code-maker :as cm]))

(defn random-code []
  (cb/number-to-guess (rand-int (dec (* 6 6 6 6))))
  )

(defn auto-play []
  (let [code (random-code)]
    (loop [n 1 past-scores [] last-guess nil]
      (let [guess (cb/break-code last-guess past-scores)
            score (cm/score code guess)]
        (if (= score [4 0])
          n
          (recur (inc n) (conj past-scores [guess score]) guess))))))
