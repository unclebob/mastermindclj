(ns mastermind.auto-play-test
  (:require [midje.sweet :refer :all]
            [mastermind.auto-play :refer :all]
            [mastermind.code-breaker :as cb]
            [mastermind.code-maker :as cm]))


(facts
  "about auto-play"
  (fact
    "Spy.  If the initial guess is correct, return 1"
    (auto-play) => 1
    (provided
      (random-code) => [0 0 0 0])
    (provided
      (cm/score [0 0 0 0] [0 0 0 0]) => [4 0])
    (provided
      (cb/break-code-seq nil []) => [0 0 0 0]))

  (fact
    "If code is [0 0 0 1] should take two tries."
    (auto-play) => 2
    (provided (random-code) => [0 0 0 1]))

  (fact
    "If code is [0 0 1 0] should take 3 tries."
    (auto-play) => 3
    (provided (random-code) => [0 0 1 0]))
  )
