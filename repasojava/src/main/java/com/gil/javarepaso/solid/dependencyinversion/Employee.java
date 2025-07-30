package com.gil.javarepaso.solid.dependencyinversion;

public class Employee {

	private Notification notification;

	public Employee(Notification notification) {
		this.notification = notification;
	}

	public void notifyUser() {
		notification.notifi();
	}
}
