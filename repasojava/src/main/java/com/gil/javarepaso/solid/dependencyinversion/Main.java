package com.gil.javarepaso.solid.dependencyinversion;

public class Main {

	public static void main(String[] args) {
		Notification notification = new EmailNotification();
		Employee employee = new Employee(notification);
		employee.notifyUser();
	}

}
