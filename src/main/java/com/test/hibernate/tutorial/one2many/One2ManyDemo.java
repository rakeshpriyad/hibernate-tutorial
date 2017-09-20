package com.test.hibernate.tutorial.one2many;

import java.util.Calendar;

import org.hibernate.Session;

import com.test.hibernate.tutorial.util.HibernateUtil;

public class One2ManyDemo {
	public static void main(String[] args) {
		System.out.println("Hibernate one to many (XML)");
		Session session = HibernateUtil.getSessionFactory("hibernate-one2many.cfg.xml").openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7054");
		stock.setStockName("PADINI2");
		session.save(stock);

		StockDailyRecord stockDailyRecords = new StockDailyRecord();
		stockDailyRecords.setPriceOpen(new Float("1.2"));
		stockDailyRecords.setPriceClose(new Float("1.1"));
		stockDailyRecords.setPriceChange(new Float("10.0"));
		stockDailyRecords.setVolume(3000000L);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		stockDailyRecords.setDate(cal.getTime());

		stockDailyRecords.setStock(stock);
		stock.getStockDailyRecords().add(stockDailyRecords);

		session.save(stockDailyRecords);

		session.getTransaction().commit();
		System.out.println("Done");
		System.exit(0);
	}
}