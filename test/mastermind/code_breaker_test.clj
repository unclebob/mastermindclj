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

  (fact
    "number-to-guess"
    (number-to-guess 0) => [0 0 0 0]
    (number-to-guess 1) => [0 0 0 1]
    (number-to-guess 6) => [0 0 1 0]
    (number-to-guess 7) => [0 0 1 1]
    (number-to-guess 43) => [0 1 1 1]
    (number-to-guess 259) => [1 1 1 1]
    (number-to-guess (dec (* 6 6 6 6))) => [5 5 5 5]
    )

  (fact
    "increment guess"
    (inc-guess [0 0 0 0]) => [0 0 0 1]
    (inc-guess [0 0 0 5]) => [0 0 1 0]
    (inc-guess [0 0 5 5]) => [0 1 0 0]
    (inc-guess [0 5 5 5]) => [1 0 0 0]
    (inc-guess [5 5 5 5]) => :overflow
    )

  (fact                                                     ;This was an experiment to make sure that past guesses cannot be repeated.
    "next-guess will not return a previous guess"
    (next-guess [0 0 0 0]
                [[[0 0 0 1] [3 1]]]) =not=> [0 0 0 1]
    )

  (fact
    "initial guess"
    (break-code-seq nil []) => [0 0 0 0])

  (fact
    "first step for code [1 2 3 4]"
    (break-code-seq
      [0 0 0 0]
      [[[0 0 0 0] [0 0]]]) => [1 1 1 1]
    )

  (fact
    "first step for code [0 0 0 1]"
    (break-code-seq
      [0 0 0 0]
      [[[0 0 0 0] [3 0]]]) => [0 0 0 1]
    )

  (fact
    "second step for code [0 0 1 0]"
    (break-code-seq
      [0 0 0 1]
      [[[0 0 0 1] [2 2]]]) => [0 0 1 0]
    )

  (fact
    "Two steps are required for [0 0 1 0]"
    (break-code-seq
      [0 0 0 0]
      [[[0 0 0 0] [3 0]]
       [[0 0 0 1] [2 2]]]) => [0 0 1 0]
    )
  )

(facts
  "3x2 strategy"
  (fact
    "first step"
    (break-code-3x2 nil []) => [0 0 1 1])
  (fact
    "second step"
    (break-code-3x2 [0 0 1 1]
                    [[[0 0 1 1] [0 0]]]) => [2 2 3 3])
  (fact
    "third step"
    (break-code-3x2 [2 2 3 3]
                    [[[0 0 1 1] [0 0]]
                     [[2 2 3 3] [0 0]]]) => [4 4 5 5])
  (fact
    "Fourth step falls back to sequential decoding"
    (break-code-3x2 [4 4 5 5]
                    [[[0 0 1 1] [0 0]]
                     [[2 2 3 3] [0 0]]
                     [[4 4 5 5] [0 4]]]) => [5 5 4 4])
  (fact
    "fifth step carries on with sequential decoding"
    (break-code-3x2 [4 4 5 5]
                    [[[0 0 1 1] [0 0]]
                     [[2 2 3 3] [0 0]]
                     [[4 4 5 5] [2 2]]
                     [[4 5 4 5] [0 4]]]) => [5 4 5 4])
    )