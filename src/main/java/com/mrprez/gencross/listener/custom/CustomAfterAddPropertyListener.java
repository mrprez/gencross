package com.mrprez.gencross.listener.custom;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.mrprez.gencross.Personnage;
import com.mrprez.gencross.Property;
import com.mrprez.gencross.listener.AfterAddPropertyListener;

public class CustomAfterAddPropertyListener extends AfterAddPropertyListener {
	private Method method;
	
	
	public CustomAfterAddPropertyListener(){
		super();
	}
	
	public CustomAfterAddPropertyListener(Personnage personnage, String methodName, String pattern) throws SecurityException, NoSuchMethodException{
		super();
		setPersonnage(personnage);
		method = personnage.getClass().getMethod(methodName, Property.class);
		setPattern(pattern);
	}
	
	@Override
	public void callAfterAddProperty(Property property) throws Exception {
		method.invoke(getPersonnage(), property);
	}

	@Override
	public Map<String, String> getArgs() {
		Map<String, String> args = new HashMap<String, String>();
		args.put("method", method.getName());
		return args;
	}

	@Override
	public void setArgs(Map<String, String> args) throws SecurityException, NoSuchMethodException {
		method = getPersonnage().getClass().getMethod(args.get("method"), Property.class);
	}


}
