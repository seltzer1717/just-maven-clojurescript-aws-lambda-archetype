;; Require cljs.main
(require '(cljs [main :as m]))

;; Import java.nio.file classes.
(import '(java.nio.file Files Paths StandardOpenOption))

;; Call main.
(apply m/-main *command-line-args*)

;; Added to provide expected exports for AWS Lambda. Clojurescript compiles a library
;; with index.js as library loader. AWS Lambda is expecting a lambda javascript file
;; with lambda handler function exported directly from that file.
;;
;; The below addition simply exports the namespaced handler as a simple global handler.
(Files/write (Paths/get "target/js/index.js" (into-array [""]))
             (.getBytes "\n\n// Exporting lambdaHandler\nexports.lambdaHandler = com.example.sample.core.handler;\n")
             (into-array [StandardOpenOption/APPEND]))