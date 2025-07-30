package com.gil.javarepaso.pattern.creational.builder;




public class TestBuilder {

	public static void main(String args[]) {

		Student student = Student.studentBuilder()
				.parentName("Andrea")
				.parentAge(38)
				.childName("Emma")
				.childAge(6)
				.schoolName("Baeldung High School").build();

		System.out.println("student.getChildName()=>"+student.getChildName());
		System.out.println("student.getChildAge()=>"+student.getChildAge());
		System.out.println("student.getParentName()=>"+student.getParentName());
		System.out.println("student.getParentAge()=>"+student.getParentAge());
		System.out.println("student.getSchoolName()=>"+student.getSchoolName());

	}
}
