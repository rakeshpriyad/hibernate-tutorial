package com.test.hibernate.tutorial.many2many.annotation;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.test.hibernate.tutorial.util.HibernateUtil;

public class Many2ManyAnnotationDemo {
	public static void main(String[] args) {
		System.out.println("Hibernate many to many (Annotation)");
		Session session = HibernateUtil.getSessionFactory("hibernate-many2many-annotation.cfg.xml").openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7061");
		stock.setStockName("PADINI7");

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