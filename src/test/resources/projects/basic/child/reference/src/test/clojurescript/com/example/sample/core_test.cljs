(ns com.example.sample.core-test
  (:require [com.example.sample.core :as c]
            [cljs.test :refer-macros [deftest is testing run-tests]]))

(deftest test-reverso (is (= "blah" (c/reverso "halb"))))

