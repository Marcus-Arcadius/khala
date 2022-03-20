(ns khala.trash)

(comment
 (defn cmd
   ""
   [& args]
   (clojure.string/join
    " "
    (map (fn [s] (->
                  (sh "q" :in (str s))
                  :out)) args))))
