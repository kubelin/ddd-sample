package com.mypetmanager.global.context;

public class MyContextHolder {
	private static final ThreadLocal<MyContext> contextHolder = new ThreadLocal<>();

	public static MyContext get() {
		if (contextHolder.get() == null) {
			contextHolder.set(new MyContext());
		}
		return contextHolder.get();
	}

	public static void clear() {
		contextHolder.remove();
	}
}
