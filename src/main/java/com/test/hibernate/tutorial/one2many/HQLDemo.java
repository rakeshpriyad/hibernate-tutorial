package com.test.hibernate.tutorial.one2many;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.test.hibernate.tutorial.util.HibernateUtil;

public class HQLDemo {
	public static void main(String[] args) {
		System.out.println("Hibernate one to many (XML)");
		Session session = HibernateUtil.getSessionFactory("hibernate-one2many.cfg.xml").openSession();
		Query query = session.createQuery("from com.test.hibernate.tutorial.one2many.Stock where stockCode = :code ");
		query.setParameter("code", "4715");
		List<com.test.hibernate.tutorial.one2many.Stock> list = query.list();
		System.out.println("xx --" + list);
		
		//before java 8
		List<com.test.hibernate.tutorial.one2many.Stock> stocks = session.createQuery("from com.test.hibernate.tutorial.one2many.Stock", com.test.hibernate.tutorial.one2many.Stock.class).getResultList();
		for (com.test.hibernate.tutorial.one2many.Stock stock : stocks) { 
		  if (stock.getStockId() == 1) { 
		    System.out.println(stock.getStockName()); 
		  } 
		}
		
		//before java 8
		List<com.test.hibernate.tutorial.one2many.Stock> stockList = session.createQuery("from com.test.hibernate.tutorial.one2many.Stock", com.test.hibernate.tutorial.one2many.Stock.class).getResultList();
		stockList.stream()
		  .filter(s -> s.getStockId() == 1)
		  .forEach(s -> System.out.println(s.getStockName()));
		
		
		//java 8
		//Since a major feature of functional programming is the compositional design, a more typical streams approach would be to chain stepwise operations on the data. To extract the name of the item, we may map the getter on the stream as follows.

		 
		stockList.stream()
		 // .filter(s -> s.getStockId() == 1)
		  .map(Stock::getStockId)
		  .forEach(System.out::println);
		
		session.createQuery("from com.test.hibernate.tutorial.one2many.Stock", com.test.hibernate.tutorial.one2many.Stock.class).stream().filter(s -> s.getStockId() == 1)
		  .map(Stock::getStockId)
		  .forEach(System.out::println);
		
		System.exit(0);
		
	}
}
