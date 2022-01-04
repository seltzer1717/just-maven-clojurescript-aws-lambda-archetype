(ns com.example.sample.test-suite
  (:require [com.example.sample.core-test :as c]
            [cljs.test :refer-macros [deftest is testing run-tests]]))

(defn -main [args] (run-tests 'com.example.sample.core-test))

;; (run-tests 'com.example.foo.core-test)
