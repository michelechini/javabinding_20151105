module namespace _ = 'urn:test';

declare namespace jb = "java:com.dedalus.dss.javabinding.JavaEvaluator";

declare variable $_:EVALUATOR := jb:uniqueInstance();

declare %unit:test function _:test-java-dummy-integer() {
  unit:assert(
    jb:eval($_:EVALUATOR, "1", "com.dedalus.dss.javabinding.evaluators.DummyEvaluator", 1) = 1)
};

declare %unit:test function _:test-java-dummy-string() {
  unit:assert(
    jb:eval($_:EVALUATOR, "2", "com.dedalus.dss.javabinding.evaluators.DummyEvaluator", "1") = "1")
};

declare %unit:test function _:test-java-dummy-boolean() {
  unit:assert(
    jb:eval($_:EVALUATOR, "3", "com.dedalus.dss.javabinding.evaluators.DummyEvaluator", true()) = true())
};

declare %unit:test function _:test-java-dummy-xml() {
  unit:assert(
    deep-equal(
      jb:eval($_:EVALUATOR, "4", "com.dedalus.dss.javabinding.evaluators.DummyEvaluator", <a/>),
      <a/>
    )
  )
};

declare %unit:test function _:test-javascript-dummy-integer() {
  unit:assert(
    jb:eval($_:EVALUATOR, "1", "com.dedalus.dss.javabinding.evaluators.DummyScriptingEvaluator", 1) = 1)
};
