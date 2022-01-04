#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
(ns ${package}.core
(:require ["@aws-sdk/client-s3" :refer [S3Client ListBucketsCommand]]))

(defn reverso [halb]
  (.join (.reverse (.split halb "")) ""))

(defn handler []
  (let [client   (S3Client. #js{:region "us-west-1"})
        command  (ListBucketsCommand. #js{})
        response {:statusCode "200" :body nil :headers {:Content-Type "application/json"}}]
    (-> (.send  client command)
        (.then  (fn [data]  (clj->js (assoc response :body (.stringify js/JSON (.-Buckets data))))))
        (.catch (fn [error] (clj->js (assoc response :statusCode "500" :body (.stringify js/JSON error))))))))
