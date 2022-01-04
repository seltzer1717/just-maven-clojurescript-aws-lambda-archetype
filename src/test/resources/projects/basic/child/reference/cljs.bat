@echo off
set clojurescript_args=%*
set clojure_args=.clojure/scripts/cljs.clj %clojurescript_args%
mvn -q exec:java@clj -Dclojure.args="%clojure_args%"
