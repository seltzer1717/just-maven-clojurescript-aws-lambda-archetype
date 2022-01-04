;; Require cljs.main
(require '(cljs [main :as m]))

;; Call main.
(apply m/-main *command-line-args*)

;; When ClojureScript REPL ends, exit Clojure
(if (= "--repl"
    (last *command-line-args*))
    (System/exit 0))
