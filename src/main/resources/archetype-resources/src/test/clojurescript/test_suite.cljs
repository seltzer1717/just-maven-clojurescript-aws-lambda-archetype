#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
(ns ${package}.test-suite
  (:require [${package}.core-test :as c]
            [cljs.test :refer-macros [deftest is testing run-tests]]))

(defn -main [args] (run-tests '${package}.core-test))

;; (run-tests 'com.example.foo.core-test)
