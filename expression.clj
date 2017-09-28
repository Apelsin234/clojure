(defn constant [value] (constantly value))
(defn variable [value] #(% value))

(defn operations [oper] (fn [& arr]
                          (fn [args] (apply oper (map #(% args) arr)))
                          ))
(def div #(/ (double %1) %2))
(def sinfn #(Math/sin %))
(def cosfn #(Math/cos %))

(def add (operations +))
(def subtract (operations -))
(def divide (operations div))
(def multiply (operations *))

(def negate (operations -))
(def sin (operations sinfn))
(def cos (operations cosfn))

(def op {'+ add, '- subtract, '* multiply, '/ divide, 'negate negate, 'sin sin, 'cos cos})


(defn parseFunction [expr] (
                             (fn parse [val] (cond
                                               (symbol? val) (variable (str val))
                                               (number? val) (constant val)
                                               (list? val) (apply (op (first val)) (map parse (rest val)))))
                             (read-string expr)))

;(defn check [da] (def expr (parseFunction "(- (* 2 x) 3)"))
;  (println (expr {"x" da})))
