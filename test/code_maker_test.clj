(ns code-maker-test
  (:require [midje.sweet :refer :all]
            [mastermind.code-maker :refer :all]))

(facts
  "code maker"
  (fact
    "score guess with no matches"
    (score [0 0 0 0] [1 1 1 1]) => [0])

  (fact
    "score guess with one position match"
    (score [0 0 0 0] [0 1 1 1]) => [1])

  (fact
    "guess with two position matches"
    (score [0 0 0 0] [0 1 1 0]) => [2]
    (score [0 0 0 0] [1 0 1 0]) => [2]
    (score [0 0 0 0] [0 1 0 1]) => [2]
    )

  (fact
    "guess with many position matches"
    (score [1 1 1 1] [0 1 1 1]) => [3]
    (score [0 0 0 0] [0 0 0 1]) => [3]
    (score [1 2 3 4] [1 2 3 4]) => [4])
  )