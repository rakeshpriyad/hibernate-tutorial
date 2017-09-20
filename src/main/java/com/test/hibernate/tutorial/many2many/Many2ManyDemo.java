package com.test.hibernate.tutorial.many2many;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.test.hibernate.tutorial.util.HibernateUtil;

public class Many2ManyDemo {
	public static void main(String[] args) {
		System.out.println("Hibernate many to many (XML)");
		Session session = HibernateUtil.getSessionFactory("hibernate-many2many.cfg.xml").openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7060");
		stock.setStockName("PADINI6");

		Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
		Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");

		Set<Category> categories = new HashSet<Category>();
		categories.add(category1);
		categories.add(category2);

		stock.setCategories(categories);

		session.save(stock);

		session.getTransaction().commit();
		System.out.println("Done");
		System.exit(0);
	}
}