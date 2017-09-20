package com.test.hibernate.tutorial.one2many.annotation;

import java.util.Date;

import org.hibernate.Session;

import com.test.hibernate.tutorial.util.HibernateUtil;

public class One2ManyDemo {
	public static void main(String[] args) {
		System.out.println("Hibernate one to many (Annotation)");
		Session session = HibernateUtil.getSessionFactory("hibernate-one2many-annotation.cfg.xml").openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7053");
		stock.setStockName("PADINI1");
		session.save(stock);

		StockDailyRecord stockDailyRecords = new StockDailyRecord();
		stockDailyRecords.setPriceOpen(new Float("1.2"));
		stockDailyRecords.setPriceClose(new Float("1.1"));
		stockDailyRecords.setPriceChange(new Float("10.0"));
		stockDailyRecords.setVolume(3000000L);
		stockDailyRecords.setDate(new Date());

		stockDailyRecords.setStock(stock);
		stock.getStockDailyRecords().add(stockDailyRecords);

		session.save(stockDailyRecords);

		session.getTransaction().commit();
		System.out.println("Done");

		System.exit(0);
	}
}