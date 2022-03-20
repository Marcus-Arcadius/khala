(defproject khala "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clj-time "0.14.0"]
                 [cheshire "5.10.2"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/core.async "1.5.648"]
                 [compojure "1.6.2"]
                 ;; Ditch http-kit
                 ;; [http-kit "2.5.0-SNAPSHOT"]
                 [clj-http "3.12.3"]
                 [clojure-data "0.1.4"]
                 ;; Use this as the http client
                 ;; [http.async.client "1.3.1"]
                 ;; [ring "2.0.0-alpha1"]
                 [ring/ring-json "0.5.1"]
                 ;; [http-kit "2.3.0"]
                 ;; [http-kit "2.1.0-SNAPSHOT"]
                 ;; [http-kit "2.2.0"]
                 ;; [http-kit "2.4.0"]
                 [http-kit "2.6.0-alpha1"]
                 [ring "1.6.0-RC3"]
                 
                 ;; rhizome
                 [io.replikativ/datahike-jdbc "0.1.2-SNAPSHOT"]
                 [io.replikativ/datahike "0.3.7-SNAPSHOT"]

                 ;; openai libpython
                 [clj-python/libpython-clj "2.018"]

                 ;; openai curl
                 [clj-http "3.12.3"]
                 [org.clojure/data.json "2.3.1"]

                 ;; pensieve
                 ;; [clj-http "3.9.1"]
                 ;; [org.clojure/core.async "0.4.500"]
                 [org.clojure/java.jdbc "0.7.8"]
                 ;; [cheshire "5.8.1"]
                 [org.clojure/tools.reader "1.3.2"]
                 [com.github.serceman/jnr-fuse "0.5.7"]]
  :main ^:skip-aot khala.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
