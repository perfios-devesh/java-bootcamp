package com.august12.package1;

public class SingletonClass {

	private static SingletonClass single_instance = null;

	public String s;

	private SingletonClass() {
		s = "Hello I am a string part of Singleton class";
	}

	// Static method
	// Static method to create instance of Singleton class
	public static SingletonClass getInstance() {
		if (single_instance == null)
			single_instance = new SingletonClass();

		return single_instance;
	}

}
