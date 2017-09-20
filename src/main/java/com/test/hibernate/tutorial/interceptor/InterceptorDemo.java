package com.test.hibernate.tutorial.interceptor;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;

import com.test.hibernate.tutorial.util.HibernateUtil;

public class InterceptorDemo {
	public static void main(String[] args) {
		System.out.println("Hibernate many to many (Annotation)");
		Session session = HibernateUtil.getSessionFactory("hibernate-interceptor.cfg.xml").withOptions().interceptor(new ConsoleLogInterceptor()).openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7062");
		stock.setStockName("PADINI8");

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