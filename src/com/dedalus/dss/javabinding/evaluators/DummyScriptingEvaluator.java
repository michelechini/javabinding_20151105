package com.dedalus.dss.javabinding.evaluators;

import javax.script.Bindings;
import javax.script.ScriptEngine;

import com.dedalus.dss.javabinding.JavaScriptingEvaluator;

public class DummyScriptingEvaluator extends JavaScriptingEvaluator {

	@Override
	protected String extractLanguage(Object data) {
		return "javascript";
	}

	@Override
	protected void setParameters(Bindings bindings, Object data) {
		bindings.put("v", data);
	}

	@Override
	protected String extractFileName(Object data) {
		return "test/javascript/example.js";
	}

	@Override
	protected Object formatOutput(Object output) {
		return output;
	}

	public static void main(String args[]) {
		DummyScriptingEvaluator e = new DummyScriptingEvaluator();
		System.out.println(e.evaluate("xxx", 1));
	}
}
