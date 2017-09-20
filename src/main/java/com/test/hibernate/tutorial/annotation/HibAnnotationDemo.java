package com.test.hibernate.tutorial.annotation;

import org.hibernate.Session;

import com.test.hibernate.tutorial.util.HibernateUtil;


public class HibAnnotationDemo {

	public static void main(String[] args) {
		System.out.println("Maven + Hibernate annotation + MySQL");
		Session session = HibernateUtil.getSessionFactory("hibernate-annotation.cfg.xml").openSession();

		session.beginTransaction();
		Stock stock = new Stock();

		stock.setStockCode("4716");
		stock.setStockName("GENM!");

		session.save(stock);
		session.getTransaction().commit();
	}
}
