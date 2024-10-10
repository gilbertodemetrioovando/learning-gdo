package com.demo.cfxsoap.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Charge {
	
	private int id;
	
	private String descripcion;
	
	private Float amount;

}
