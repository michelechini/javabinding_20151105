package com.dedalus.dss.javabinding;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public abstract class JavaScriptingEvaluator implements JavaEvaluatorInterface {

	ScriptEngineManager manager;
	
	protected JavaScriptingEvaluator() {
		this.manager = new ScriptEngineManager();
	}
	
	protected abstract String extractLanguage(Object data);
	
	protected abstract void setParameters(Bindings bindings, Object data);
	
	protected abstract String extractFileName(Object data);
	
	protected abstract Object formatOutput(Object output);
	
	@Override
	public Object evaluate(String id, Object data) {
		
		String lang = this.extractLanguage(data);
		
		if (lang == null) throw new IllegalArgumentException(id + ": Unable to extract language specification from parameters");
		
		ScriptEngine engine = manager.getEngineByName(lang);
		if (engine == null) { engine = manager.getEngineByExtension(lang); }
		if (engine == null) { engine = manager.getEngineByMimeType(lang); }
		if (engine == null) throw new IllegalArgumentException(id + ":" + lang + " is not supported.");
		
		Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE); 
		bindings.clear();
		setParameters(bindings, data);
		
		String fileName = extractFileName(data);
		Object toret = null;
		try {
		
			toret = engine.eval(new FileReader(fileName));
		
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(id + ": Unable to find file " + fileName);
		} catch (ScriptException e) {
			throw new IllegalArgumentException(id + ": Script is not valid " + fileName);
		}
		
		return formatOutput(toret);
	}

}
