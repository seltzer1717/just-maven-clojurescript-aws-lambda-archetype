;; Require cljs.main
;; Require cljs.main
(require '(cljs [main :as m]))

;; Call main.
(apply m/-main *command-line-args*)
