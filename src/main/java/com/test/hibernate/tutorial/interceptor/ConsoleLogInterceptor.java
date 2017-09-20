package com.test.hibernate.tutorial.interceptor;
import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
 
public class ConsoleLogInterceptor extends EmptyInterceptor{
 
      private static final long serialVersionUID = 1L;
 
      @Override
      public void onDelete(Object entity, Serializable id, Object[] state,
             String[] propertyNames, Type[] types) {
             System.out.println("onDelete Method is getting called");
             System.out.println("==== DETAILS OF ENTITY ARE ====");
             if(entity instanceof Stock)
             {
                System.out.println("Id of an Entity is :" + id);
                System.out.println("Property Names ");
             
                for(int i=0;i<propertyNames.length;i++)
                {
                System.out.println(propertyNames[i] );
                }
 
                Stock stock = (Stock) entity;            
                System.out.println("Stock STATE is ");
                System.out.println(stock);
        }
      }
 
     @Override
     public boolean onLoad(Object entity, Serializable id, Object[] state,
                String[] propertyNames, Type[] types) {
            System.out.println("onLoad Method is getting called");
 
            System.out.println("==== DETAILS OF ENTITY ARE ====");
            if(entity instanceof Stock)
            {
               System.out.println("Id of an Entity is :" + id);
               System.out.println("Property Names ");
 
               for(int i=0;i<propertyNames.length;i++)
               {
                  System.out.println(propertyNames[i] );
               }
  
               Stock stock = (Stock) entity;
 
              System.out.println("Stock STATE is ");
              System.out.println(stock);
        }
        return true;
     }
 
     @Override
     public boolean onSave(Object entity, Serializable id, Object[] state,
            String[] propertyNames, Type[] types) {
        System.out.println("onsave Method is getting called");
        System.out.println("==== DETAILS OF ENTITY ARE ====");
        if(entity instanceof Stock)
        {
            System.out.println("Id of an Entity is :" + id);
            System.out.println("Property Names ");
 
            for(int i=0;i<propertyNames.length;i++)
            {
                System.out.println(propertyNames[i] );
 
                if("stockName".equals(propertyNames[i]))
                {
                    state[i]= "Stock Updated";
                }
            }
 
            Stock stock = (Stock)entity;
            System.out.println("Stock STATE is ");
            System.out.println(stock);
        }
        return true;
    }    
}