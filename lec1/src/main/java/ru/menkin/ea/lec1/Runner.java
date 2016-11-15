package ru.menkin.ea.lec1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author menkin
 * @since 2.12.2015
 */
public class Runner {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext( "classpath:beans.xml" );
	}
}
