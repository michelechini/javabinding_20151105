package com.dedalus.dss.javabinding;

public class JavaEvaluator {

	static private JavaEvaluator instance;
	
	private JavaEvaluator(){};
	
	public static synchronized JavaEvaluator uniqueInstance(){
		if(instance == null){
			instance = new JavaEvaluator();
		}
		return instance;
	}
	
	public Object eval(String id, String className, Object data) throws Exception{
		Class ei = Class.forName(className);
		System.out.println("instantiating " + className);
		JavaEvaluatorInterface evaluator = (JavaEvaluatorInterface)ei.newInstance();

		return evaluator.evaluate(id, data);
	}
}
