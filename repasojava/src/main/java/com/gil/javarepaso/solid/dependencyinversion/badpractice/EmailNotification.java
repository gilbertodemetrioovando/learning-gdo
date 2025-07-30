package com.gil.javarepaso.solid.dependencyinversion.badpractice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailNotification implements Notification{

	@Override
	public void notifi() {
		log.info("Notificando...");
	}

}
