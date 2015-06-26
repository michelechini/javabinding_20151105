package com.dedalus.dss.javabinding.evaluators;

import com.dedalus.dss.javabinding.JavaEvaluatorInterface;

public class DummyEvaluator implements JavaEvaluatorInterface {

	@Override
	public Object evaluate(String id, Object data) {
		System.out.println(data.getClass());
		return data;
	}

}
