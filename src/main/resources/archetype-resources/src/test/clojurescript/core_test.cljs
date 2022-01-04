#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
(ns ${package}.core-test
  (:require [${package}.core :as c]
            [cljs.test :refer-macros [deftest is testing run-tests]]))

(deftest test-reverso (is (= "blah" (c/reverso "halb"))))

