package me.day11.singleton.classic;

public class SingletonClient {
	public static void main(String[] args) {
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		System.out.println(singleton1 == singleton2);
		System.out.println(singleton1.getDescription());
		System.out.println(singleton2.getDescription());
	}
}
