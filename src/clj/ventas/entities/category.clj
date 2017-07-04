(ns ventas.entities.category
  (:require [clojure.spec :as s]
            [clojure.core.async :refer [<! go-loop]]
            [clojure.test.check.generators :as gen]
            [com.gfredericks.test.chuck.generators :as gen']
            [ventas.database :as db]
            [ventas.events :as events]))

(s/def :category/name string?)
(s/def :category/parent
  (s/with-gen integer? #(gen/elements (map :id (db/entity-query :category)))))

(s/def :category/image
  (s/with-gen integer? #(gen/elements (map :id (db/entity-query :file)))))

(s/def :schema.type/category
  (s/keys :req [:category/name]
          :opt [:category/image :category/parent]))

(defmethod db/entity-fixtures :category [_]
  [{:name "Default"}])