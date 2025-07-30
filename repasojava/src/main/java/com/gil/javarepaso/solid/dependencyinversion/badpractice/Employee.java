package com.gil.javarepaso.solid.dependencyinversion.badpractice;

public class Employee {

	private EmailNotification emailNotification;

	public Employee(EmailNotification emailNotification) {
		this.emailNotification = emailNotification;
	}

	public void notifyUser() {
		emailNotification.notifi();
	}
}
