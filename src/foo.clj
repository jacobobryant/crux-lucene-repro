(ns foo
  (:require
    [clojure.java.io :as io]
    [crux.api :as crux]))

(defn -main []
  (println "starting node")
  (let [node (crux/start-node
               {:crux/index-store {:kv-store {:crux/module 'crux.rocksdb/->kv-store
                                              :db-dir (io/file "data/index")}}
                :rocksdb-golden {:crux/module 'crux.rocksdb/->kv-store
                                 :db-dir (io/file "data/golden")}
                :crux/document-store {:kv-store :rocksdb-golden}
                :crux/tx-log {:kv-store :rocksdb-golden}
                :crux.lucene/lucene-store {:db-dir "data/lucene"}
                :crux.http-server/server {:port 3000}})]
    (println "submitting tx")
    (crux/await-tx node (crux/submit-tx node [[:crux.tx/put {:crux.db/id :foo}]]))
    (println "closing node")
    (.close node)
    (println "exiting")
    (System/exit 0)))
