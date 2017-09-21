package com.test.hibernate.tutorial.one2many;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.test.hibernate.tutorial.util.HibernateUtil;

public class CriteriaQueryExample {

	public static void main(String[] args) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory("hibernate-one2many.cfg.xml").openSession();) {
			transaction = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Stock> query = builder.createQuery(Stock.class);
			Root<Stock> root = query.from(Stock.class);
			query.select(root);
			Query<Stock> q = session.createQuery(query);
			List<Stock> stocks = q.getResultList();
			for (Stock stock : stocks) {
				System.out.println(stock.getStockName());
			}
			
			stocks.stream()//.filter(s -> s.getStockId() == 1)
			.forEach(s -> System.out.println(s.getStockName()));
			
			// select * from stock where stockid =1
			query.select(root).where(builder.equal(root.get("stockId"), 1));
			Query<Stock> q1 = session.createQuery(query);
			Stock s =q1.getSingleResult();
			System.out.println(s.getStockCode()+ " s name: "+ s.getStockName());
			

			 CriteriaBuilder builder1 = session.getCriteriaBuilder();
	         CriteriaQuery<String> query1 = builder1.createQuery(String.class);
	         Root<Stock> root1 = query1.from(Stock.class);
	         query1.select(root1.get("stockName"));
	         Query<String> q2=session.createQuery(query1);
	         List<String> list=q2.getResultList();
	         for (String stockName : list) {
	            System.out.println("Stock Name: " + stockName);
	         }

			
	         CriteriaBuilder builderArr = session.getCriteriaBuilder();
	         CriteriaQuery<Object[]> queryArr = builderArr.createQuery(Object[].class);
	         Root<Stock> rootArr = queryArr.from(Stock.class);
	         queryArr.multiselect(rootArr.get("stockName"),rootArr.get("stockCode"));
	         Query<Object[]> q3=session.createQuery(queryArr);
	         List<Object[]> list1=q3.getResultList();
	         for (Object[] objects : list1) {
	            System.out.println("Name : "+objects[0]);
	            System.out.println("code : "+objects[1]);
	         }
	         
	         
	      // Count number of Stock
	         CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
	         Root<Stock> root0 = criteriaQuery.from(Stock.class);
	         criteriaQuery.select(builder.count(root0));
	         Query<Long> query0 = session.createQuery(criteriaQuery);
	         long count = query0.getSingleResult();
	         System.out.println("Count = " + count);

	         // Get max avgStockId
	         CriteriaQuery<Integer> criteriaQuery2 = builder.createQuery(Integer.class);
	         Root<Stock> root2 = criteriaQuery2.from(Stock.class);
	         criteriaQuery2.select(builder.max(root2.get("stockId")));
	         Query<Integer> query2 = session.createQuery(criteriaQuery2);
	         int maxstockId = query2.getSingleResult();
	         System.out.println("Max stockId = " + maxstockId);

	         // Get Average avgStockId
	         CriteriaQuery<Double> criteriaQuery3 = builder.createQuery(Double.class);
	         Root<Stock> root3 = criteriaQuery3.from(Stock.class);
	         criteriaQuery3.select(builder.avg(root3.get("stockId")));
	         Query<Double> query3 = session.createQuery(criteriaQuery3);
	         double avgStockId = query3.getSingleResult();
	         System.out.println("Average stockId = " + avgStockId);

	         // Count distinct Stocks
	         CriteriaQuery<Long> criteriaQuery4 = builder.createQuery(Long.class);
	         Root<Stock> root4 = criteriaQuery4.from(Stock.class);
	         criteriaQuery4.select(builder.countDistinct(root4));
	         Query<Long> query4 = session.createQuery(criteriaQuery4);
	         long distinct = query4.getSingleResult();
	         System.out.println("Distinct count = " + distinct);

	         
	         CriteriaBuilder builderJoin = session.getCriteriaBuilder();

	         // Using FROM and JOIN
	         CriteriaQuery<Object[]> criteriaQueryJoin = builderJoin.createQuery(Object[].class);
	         Root<Stock> stockRoot = criteriaQueryJoin.from(Stock.class);
	         Root<StockDailyRecord> stockDailyRoot = criteriaQueryJoin.from(StockDailyRecord.class);
	         criteriaQueryJoin.multiselect(stockRoot, stockDailyRoot);
	         criteriaQueryJoin.where(builderJoin.equal(stockRoot.get("stockId"), stockDailyRoot.get("stock").get("stockId")));

	         Query<Object[]> queryJoin=session.createQuery(criteriaQueryJoin);
	         List<Object[]> listJoin=queryJoin.getResultList();
	         for (Object[] objects : listJoin) {
	            Stock stock=(Stock)objects[0];
	            StockDailyRecord record=(StockDailyRecord)objects[1];
	            System.out.println("Stock NAME="+stock.getStockName()+"\t Daily record NAME=" + record.getPriceChange());
	         }
	         
	         
	         /**
	          * Group by example
	          *  CriteriaBuilder builder = session.getCriteriaBuilder();

         CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
         Root<Employee> root = criteriaQuery.from(Employee.class);
         criteriaQuery.multiselect(builder.count(root.get("name")), root.get("salary"),
               root.get("department"));
         criteriaQuery.groupBy(root.get("salary"), root.get("department"));
         criteriaQuery.having(builder.greaterThan(root.get("salary"), 30000));

         Query<Object[]> query = session.createQuery(criteriaQuery);
         List<Object[]> list = query.getResultList();
         for (Object[] objects : list) {
            long count = (Long) objects[0];
            int salary = (Integer) objects[1];
            Department department = (Department) objects[2];
            System.out.println("Number of Employee = " + count + "\t SALARY=" + salary
                  + "\t DEPT NAME=" + department.getName());
         }

	          */
	         
	         /**
	          * Order by example
	          */
	         /**
	          *  CriteriaBuilder builder = session.getCriteriaBuilder();

         CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
         Root<Employee> root = criteriaQuery.from(Employee.class);
         criteriaQuery.select(root);
         criteriaQuery.orderBy(builder.asc(root.get("salary")));
         Query<Employee> query = session.createQuery(criteriaQuery);
         List<Employee> list = query.getResultList();
         for (Employee employee : list) {
            System.out.println("EMP NAME="+employee.getName()+"\t SALARY="+employee.getSalary());
         }

	          */
	         
	         
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}

	}
}
