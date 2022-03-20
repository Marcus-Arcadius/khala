(ns khala.trash)

(use '[clojure.string :only (join split upper-case)])

(comment
  (defn cmd
    ""
    [& args]
    (join
     " "
     (map (fn [s] (->
                   (sh "q" :in (str s))
                   :out)) args))))
