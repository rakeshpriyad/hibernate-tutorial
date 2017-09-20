package com.test.hibernate.tutorial.one2one;

import java.util.Date;

import org.hibernate.Session;

import com.test.hibernate.tutorial.util.HibernateUtil;

public class One2OneDemo {
	public static void main(String[] args) {
		System.out.println("Hibernate one to one (XML mapping)");
		Session session = HibernateUtil.getSessionFactory("hibernate-one2one.cfg.xml").openSession();

		session.beginTransaction();

		Stock stock = new Stock();

		stock.setStockCode("4719");
		stock.setStockName("GENO");

		StockDetail stockDetail = new StockDetail();
		stockDetail.setCompName("GENTING Malaysia");
		stockDetail.setCompDesc("Best resort in the world");
		stockDetail.setRemark("Nothing Special");
		stockDetail.setListedDate(new Date());

		stock.setStockDetail(stockDetail);
		stockDetail.setStock(stock);

		session.save(stock);
		session.getTransaction().commit();

		System.out.println("Done");
		
		System.exit(0);
	}
}