(ns mastermind.auto-play
  (:require
    [mastermind.code-breaker :as cb]
    [mastermind.code-maker :as cm]))

(defn random-code []
  (cb/number-to-guess (rand-int (dec (* 6 6 6 6))))
  )

(defn auto-play
  ([]
   (auto-play cb/break-code-seq))
  ([strategy]
   (let [code (random-code)]
     (loop [n 1 past-scores [] last-guess nil]
       (let [guess (strategy last-guess past-scores)
             score (cm/score code guess)]
         (if (= score [4 0])
           n
           (recur (inc n) (conj past-scores [guess score]) guess)))))))

(def square #(* % %))

(defn mean [x]
  (/ (reduce + x) (count x)))

(defn sigma [x]
  (let [mn (mean x)]
    (Math/sqrt
      (/ (reduce #(+ %1 (square (- %2 mn))) 0 x)
         (dec (count x))))))

(defn analyze-strategy [strategy n]
  (let [scores (sort (for [x (repeat n nil)] (auto-play strategy)))]
    {:mean (double (mean scores))
     :sigma (sigma scores)
     :min (first scores)
     :max (last scores)
     :median (nth scores (int (/ (count scores) 2)))
     :hist (map count (partition-by identity scores))
     }))