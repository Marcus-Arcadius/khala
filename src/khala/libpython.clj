(ns khala.libpython
  (:require
   [libpython-clj2.require :refer [require-python]]
   [libpython-clj2.python :refer [py. py.. py.-]:as py]))

(require-python '[openai :as oai])

(def oai-completion (oai/Completion))

(defn complete []
  "Run openai completion"
  (let*
      [score_multiplier 100.0
       api_token (System/getenv "PEN_KEY")
       pen_model (System/getenv "PEN_MODEL")
       pen_prompt (System/getenv "PEN_PROMPT")
       pen_suffix (System/getenv "PEN_SUFFIX")
       pen_payloads (System/getenv "PEN_PAYLOADS")
       pen_documents (System/getenv "PEN_DOCUMENTS")
       pen_mode (System/getenv "PEN_MODE")
       pen_temperature (System/getenv "PEN_TEMPERATURE")
       pen_stop_sequences (System/getenv "PEN_STOP_SEQUENCES")
       pen_stop_sequence (System/getenv "PEN_STOP_SEQUENCE")
       pen_logprobs (System/getenv "PEN_LOGPROBS")
       pen_end_pos (or (System/getenv "PEN_END_POS")
                       (count pen_prompt))
       collect_from_pos (or (System/getenv "PEN_COLLECT_FROM_POS")
                            pen_end_pos)
       pen_top_k (System/getenv "PEN_TOP_K")
       pen_top_p (System/getenv "PEN_TOP_P")
       pen_search_threshold (System/getenv "PEN_SEARCH_THRESHOLD")
       pen_query (System/getenv "PEN_QUERY")
       pen_gen_uuid (System/getenv "PEN_GEN_UUID")
       pen_gen_time (System/getenv "PEN_GEN_TIME")]

      (println pen_prompt)

      (py. oai-completion create :prompt "Once upon a time" :engine "davinci" :max_tokens 50)

      ;; This makes it quit faster, but libpython-clj has a slow startup time
      (System/exit 0)))
