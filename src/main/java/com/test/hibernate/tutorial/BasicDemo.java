package com.test.hibernate.tutorial;

import org.hibernate.Session;

import com.test.hibernate.tutorial.util.HibernateUtil;

public class BasicDemo {

	public static void main(String[] args) {
		System.out.println("Maven + Hibernate + MySQL");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		Stock stock = new Stock();

		stock.setStockCode("4717");
		stock.setStockName("GENN");

		session.save(stock);
		session.getTransaction().commit();
		System.exit(0);
	}
}
