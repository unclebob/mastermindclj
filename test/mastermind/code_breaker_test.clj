(ns mastermind.code-breaker-test
  (:require [midje.sweet :refer :all]
            [mastermind.code-breaker :refer :all]
            [mastermind.code-maker :as cm]))

(facts
  "Code Breaker"
  (fact
    "guess-to-number"
    (guess-to-number [0 0 0 0]) => 0
    (guess-to-number [0 0 0 1]) => 1
    (guess-to-number [0 0 1 0]) => 6
    (guess-to-number [0 0 1 1]) => 7
    (guess-to-number [0 1 1 1]) => 43
    (guess-to-number [1 1 1 1]) => 259
    (guess-to-number [5 5 5 5]) => (dec (* 6 6 6 6)))

  (future-fact
    "increment guess"
    (inc-guess [0 0 0 0]) => [0 0 0 1])

  (fact
    "initial guess"
    (break-code []) => [0 0 0 0])

  (future-fact
    "Walk through solution of code [1 2 3 4]"
    (break-code [[[0 0 0 0]
                  (cm/score [1 2 3 4] [0 0 0 0])]]) => [0 0 0 1])

  )
