Difference between get() vs load() method in Hibernate? (detailed answer)
This is one of the most frequently asked Hibernate interview question, I have seen it several times. The key difference between get() and load() method is that load() will throw an exception if an object with id passed to them is not found, but get() will return null. Another important difference is that load can return proxy without hitting the database unless required (when you access any attribute other than id) but get() always go to the database, so sometimes using load() can be faster than the get() method. It makes sense to use the load() method if you know the object exists but get() method if you are not sure about object's existence.

What is N+1 SELECT problem in Hibernate? (detailed answer)
The N+1 SELECT problem is a result of lazy loading and load on demand fetching strategy. In this case, Hibernate ends up executing N+1 SQL queries to populate a collection of N elements. For example, if you have a List of N Items where each Item has a dependency on a collection of Bid object. Now if you want to find the highest bid for each item then Hibernate will fire 1 query to load all items and N subsequent queries to load Bid for each item. So in order to find the highest bid for each item your application end up firing N+1 queries.  It's one of the important Hibernate interview questions and I suggest to read chapter 13 of Java Persistence with Hibernate to understand this problem in more details.

What are some strategies to solve the N+1 SELECT problem in Hibernate? (detailed answer)
This is the follow-up question of previous Hibernate interview question. If you answer the last query correctly then you would be most likely asked this one. Here are some strategies to solve the N+1 problem:
1) pre-fetching in batches, this will reduce N+1 problem to N/K + 1 problem where  K is size of batch
2) subselect fetching strategy
3) disabling lazy loading

What is the difference between save() and persist() method in Hibernate? (detailed answer)
Main difference between save() and persist() method is that, save returns a Serializable object while return type of persist() method is void, so it doesn't return anything. Here is a nice diagram which explains the state transition in Hibernate:


What are different types of caches available in Hibernate? (detailed answer)
This is another common Hibernate interview question. Hibernate provides the out-of-box caching solution but there are many caches e.g. first level cache, second level cache and query cache. First level cache is maintained at Session level and cannot be disabled but the second level cache is required to be configured with external cache provider like EhCache.


What is the difference between first and second level cache in Hibernate? (detailed answer)
This is again follow-up of previous Hibernate interview question. The first level cache is maintained at Session level while the second level cache is maintained at SessionFactory level and shared by all sessions. You can read these books to learn more about caching in Hibernate.


Does Hibernate Session interface is thread-safe in Java? (detailed answer)
No, Session object is not thread-safe in Hibernate and intended to be used with-in single thread in the application.


Does SessionFactory is thread-safe in Hibernate? (detailed answer)
SessionFactory is both Immutable and thread-safe and it has just one single instance in Hibernate application. It is used to create Session object and it also provide caching by storing SQL queries stored by multiple session. The second level cache is maintained at SessionFactory level. This can be a difficult and tricky question for less experienced Java developers who are not familiar with thread-safety and Immutability.

What is different between Session and Sessionfactory in Hibernate? (detailed answer)
This is another popular Hibernate interview question, mostly at a telephonic round of interviews. The main difference between Session and SessionFactory is that former is a single-threaded, short-lived object while later is Immutable and shared by all Session. It also lives until the Hibernate is running. Another difference between Session and SessionFactory is that former provides first level cache while SessionFactory provides the Second level cache.


What is difference between getCurrentSession() and openSession() in Hibernate?



What is Hibernate Framework?

Object-relational mapping or ORM is the programming technique to map application domain model objects to the relational database tables. Hibernate is java based ORM tool that provides framework for mapping application domain objects to the relational database tables and vice versa.


Hibernate provides reference implementation of Java Persistence API, that makes it a great choice as ORM tool with benefits of loose coupling. We can use Hibernate persistence API for CRUD operations. Hibernate framework provide option to map plain old java objects to traditional database tables with the use of JPA annotations as well as XML based configuration.

Similarly hibernate configurations are flexible and can be done from XML configuration file as well as programmatically. For a quick overview of hibernate framework usage, you can go through Hibernate Beginners Tutorial.

What is Java Persistence API (JPA)?

Java Persistence API (JPA) provides specification for managing the relational data in applications. Current JPA version 2.1 was started in July 2011 as JSR 338. JPA 2.1 was approved as final on 22 May 2013.


JPA specifications is defined with annotations in javax.persistence package. Using JPA annotation helps us in writing implementation independent code.


What are the important benefits of using Hibernate Framework?

Some of the important benefits of using hibernate framework are:

Hibernate eliminates all the boiler-plate code that comes with JDBC and takes care of managing resources, so we can focus on business logic.
Hibernate framework provides support for XML as well as JPA annotations, that makes our code implementation independent.
Hibernate provides a powerful query language (HQL) that is similar to SQL. However, HQL is fully object-oriented and understands concepts like inheritance, polymorphism and association.
Hibernate is an open source project from Red Hat Community and used worldwide. This makes it a better choice than others because learning curve is small and there are tons of online documentations and help is easily available in forums.
Hibernate is easy to integrate with other Java EE frameworks, it’s so popular that Spring Framework provides built-in support for integrating hibernate with Spring applications.
Hibernate supports lazy initialization using proxy objects and perform actual database queries only when it’s required.
Hibernate cache helps us in getting better performance.
For database vendor specific feature, hibernate is suitable because we can also execute native sql queries.
Overall hibernate is the best choice in current market for ORM tool, it contains all the features that you will ever need in an ORM tool.


What are the advantages of Hibernate over JDBC?

Some of the important advantages of Hibernate framework over JDBC are:

Hibernate removes a lot of boiler-plate code that comes with JDBC API, the code looks more cleaner and readable.
Hibernate supports inheritance, associations and collections. These features are not present with JDBC API.
Hibernate implicitly provides transaction management, in fact most of the queries can’t be executed outside transaction. In JDBC API, we need to write code for transaction management using commit and rollback. Read more at JDBC Transaction Management.
JDBC API throws SQLException that is a checked exception, so we need to write a lot of try-catch block code. Most of the times it’s redundant in every JDBC call and used for transaction management. Hibernate wraps JDBC exceptions and throw JDBCException or HibernateException un-checked exception, so we don’t need to write code to handle it. Hibernate built-in transaction management removes the usage of try-catch blocks.
Hibernate Query Language (HQL) is more object oriented and close to java programming language. For JDBC, we need to write native sql queries.
Hibernate supports caching that is better for performance, JDBC queries are not cached hence performance is low.
Hibernate provide option through which we can create database tables too, for JDBC tables must exist in the database.
Hibernate configuration helps us in using JDBC like connection as well as JNDI DataSource for connection pool. This is very important feature in enterprise application and completely missing in JDBC API.
Hibernate supports JPA annotations, so code is independent of implementation and easily replaceable with other ORM tools. JDBC code is very tightly coupled with the application.

Name some important interfaces of Hibernate framework?

Some of the important interfaces of Hibernate framework are:

SessionFactory (org.hibernate.SessionFactory): SessionFactory is an immutable thread-safe cache of compiled mappings for a single database. We need to initialize SessionFactory once and then we can cache and reuse it. SessionFactory instance is used to get the Session objects for database operations.
Session (org.hibernate.Session): Session is a single-threaded, short-lived object representing a conversation between the application and the persistent store. It wraps JDBC java.sql.Connection and works as a factory for org.hibernate.Transaction. We should open session only when it’s required and close it as soon as we are done using it. Session object is the interface between java application code and hibernate framework and provide methods for CRUD operations.
Transaction (org.hibernate.Transaction): Transaction is a single-threaded, short-lived object used by the application to specify atomic units of work. It abstracts the application from the underlying JDBC or JTA transaction. A org.hibernate.Session might span multiple org.hibernate.Transaction in some cases.

What is hibernate configuration file?

Hibernate configuration file contains database specific configurations and used to initialize SessionFactory. We provide database credentials or JNDI resource information in the hibernate configuration xml file. Some other important parts of hibernate configuration file is Dialect information, so that hibernate knows the database type and mapping file or class details.


What is hibernate mapping file?

Hibernate mapping file is used to define the entity bean fields and database table column mappings. We know that JPA annotations can be used for mapping but sometimes XML mapping file comes handy when we are using third party classes and we can’t use annotations.


Name some important annotations used for Hibernate mapping?

Hibernate supports JPA annotations and it has some other annotations in org.hibernate.annotations package. Some of the important JPA and hibernate annotations used are:

javax.persistence.Entity: Used with model classes to specify that they are entity beans.
javax.persistence.Table: Used with entity beans to define the corresponding table name in database.
javax.persistence.Access: Used to define the access type, either field or property. Default value is field and if you want hibernate to use getter/setter methods then you need to set it to property.
javax.persistence.Id: Used to define the primary key in the entity bean.
javax.persistence.EmbeddedId: Used to define composite primary key in the entity bean.
javax.persistence.Column: Used to define the column name in database table.
javax.persistence.GeneratedValue: Used to define the strategy to be used for generation of primary key. Used in conjunction with javax.persistence.GenerationType enum.
javax.persistence.OneToOne: Used to define the one-to-one mapping between two entity beans. We have other similar annotations as OneToMany, ManyToOne and ManyToMany
org.hibernate.annotations.Cascade: Used to define the cascading between two entity beans, used with mappings. It works in conjunction with org.hibernate.annotations.CascadeType
javax.persistence.PrimaryKeyJoinColumn: Used to define the property for foreign key. Used with org.hibernate.annotations.GenericGenerator and org.hibernate.annotations.Parameter
Here are two classes showing usage of these annotations.

package com.journaldev.hibernate.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "EMPLOYEE")
@Access(value=AccessType.FIELD)
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private long id;

	@Column(name = "emp_name")
	private String name;

	@OneToOne(mappedBy = "employee")
	@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
	private Address address;

	//getter setter methods
}
package com.journaldev.hibernate.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ADDRESS")
@Access(value=AccessType.FIELD)
public class Address {

	@Id
	@Column(name = "emp_id", unique = true, nullable = false)
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = { @Parameter(name = "property", value = "employee") })
	private long id;

	@Column(name = "address_line1")
	private String addressLine1;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Employee employee;

	//getter setter methods
}

What is Hibernate SessionFactory and how to configure it?

SessionFactory is the factory class used to get the Session objects. SessionFactory is responsible to read the hibernate configuration parameters and connect to the database and provide Session objects. Usually an application has a single SessionFactory instance and threads servicing client requests obtain Session instances from this factory.

The internal state of a SessionFactory is immutable. Once it is created this internal state is set. This internal state includes all of the metadata about Object/Relational Mapping.

SessionFactory also provide methods to get the Class metadata and Statistics instance to get the stats of query executions, second level cache details etc.


Hibernate SessionFactory is thread safe?

Internal state of SessionFactory is immutable, so it’s thread safe. Multiple threads can access it simultaneously to get Session instances.


What is Hibernate Session and how to get it?

Hibernate Session is the interface between java application layer and hibernate. This is the core interface used to perform database operations. Lifecycle of a session is bound by the beginning and end of a transaction.

Session provide methods to perform create, read, update and delete operations for a persistent object. We can execute HQL queries, SQL native queries and create criteria using Session object.


Hibernate Session is thread safe?

Hibernate Session object is not thread safe, every thread should get it’s own session instance and close it after it’s work is finished.


What is difference between openSession and getCurrentSession?

Hibernate SessionFactory getCurrentSession() method returns the session bound to the context. But for this to work, we need to configure it in hibernate configuration file. Since this session object belongs to the hibernate context, we don’t need to close it. Once the session factory is closed, this session object gets closed.

<property name="hibernate.current_session_context_class">thread</property>
Hibernate SessionFactory openSession() method always opens a new session. We should close this session object once we are done with all the database operations. We should open a new session for each request in multi-threaded environment.

There is another method openStatelessSession() that returns stateless session, for more details with examples please read Hibernate openSession vs getCurrentSession.


What is difference between Hibernate Session get() and load() method?

Hibernate session comes with different methods to load data from database. get and load are most used methods, at first look they seems similar but there are some differences between them.

get() loads the data as soon as it’s called whereas load() returns a proxy object and loads data only when it’s actually required, so load() is better because it support lazy loading.
Since load() throws exception when data is not found, we should use it only when we know data exists.
We should use get() when we want to make sure data exists in the database.
For clarification regarding the differences, please read Hibernate get vs load.


What is hibernate caching? Explain Hibernate first level cache?

As the name suggests, hibernate caches query data to make our application faster. Hibernate Cache can be very useful in gaining fast application performance if used correctly. The idea behind cache is to reduce the number of database queries, hence reducing the throughput time of the application.

Hibernate first level cache is associated with the Session object. Hibernate first level cache is enabled by default and there is no way to disable it. However hibernate provides methods through which we can delete selected objects from the cache or clear the cache completely.
Any object cached in a session will not be visible to other sessions and when the session is closed, all the cached objects will also be lost.

For better explanation, please read Hibernate First Level Cache.


How to configure Hibernate Second Level Cache using EHCache?

EHCache is the best choice for utilizing hibernate second level cache. Following steps are required to enable EHCache in hibernate application.

Add hibernate-ehcache dependency in your maven project, if it’s not maven then add corresponding jars.
<dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-ehcache</artifactId>
        <version>4.3.5.Final</version>
</dependency>
Add below properties in hibernate configuration file.
<property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
         
<!-- For singleton factory -->
<!-- <property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory</property>
-->
          
<!-- enable second level cache and query cache -->
<property name="hibernate.cache.use_second_level_cache">true</property>
<property name="hibernate.cache.use_query_cache">true</property>
<property name="net.sf.ehcache.configurationResourceName">/myehcache.xml</property>
Create EHCache configuration file, a sample file myehcache.xml would look like below.
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:noNamespaceSchemaLocation="ehcache.xsd" updateCheck="true"
    monitoring="autodetect" dynamicConfig="true">
 
    <diskStore path="java.io.tmpdir/ehcache" />
 
    <defaultCache maxEntriesLocalHeap="10000" eternal="false"
        timeToIdleSeconds="120" timeToLiveSeconds="120" diskSpoolBufferSizeMB="30"
        maxEntriesLocalDisk="10000000" diskExpiryThreadIntervalSeconds="120"
        memoryStoreEvictionPolicy="LRU" statistics="true">
        <persistence strategy="localTempSwap" />
    </defaultCache>
 
    <cache name="employee" maxEntriesLocalHeap="10000" eternal="false"
        timeToIdleSeconds="5" timeToLiveSeconds="10">
        <persistence strategy="localTempSwap" />
    </cache>
 
    <cache name="org.hibernate.cache.internal.StandardQueryCache"
        maxEntriesLocalHeap="5" eternal="false" timeToLiveSeconds="120">
        <persistence strategy="localTempSwap" />
    </cache>
 
    <cache name="org.hibernate.cache.spi.UpdateTimestampsCache"
        maxEntriesLocalHeap="5000" eternal="true">
        <persistence strategy="localTempSwap" />
    </cache>
</ehcache>
Annotate entity beans with @Cache annotation and caching strategy to use. For example,
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "ADDRESS")
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="employee")
public class Address {

}
That’s it, we are done. Hibernate will use the EHCache for second level caching, read Hibernate EHCache Example for a complete example with explanation.



What are different states of an entity bean?

An entity bean instance can exist is one of the three states.

Transient: When an object is never persisted or associated with any session, it’s in transient state. Transient instances may be made persistent by calling save(), persist() or saveOrUpdate(). Persistent instances may be made transient by calling delete().
Persistent: When an object is associated with a unique session, it’s in persistent state. Any instance returned by a get() or load() method is persistent.
Detached: When an object is previously persistent but not associated with any session, it’s in detached state. Detached instances may be made persistent by calling update(), saveOrUpdate(), lock() or replicate(). The state of a transient or detached instance may also be made persistent as a new persistent instance by calling merge().

What is use of Hibernate Session merge() call?

Hibernate merge can be used to update existing values, however this method create a copy from the passed entity object and return it. The returned object is part of persistent context and tracked for any changes, passed object is not tracked. For example program, read Hibernate merge.


What is difference between Hibernate save(), saveOrUpdate() and persist() methods?

Hibernate save can be used to save entity to database. Problem with save() is that it can be invoked without a transaction and if we have mapping entities, then only the primary object gets saved causing data inconsistencies. Also save returns the generated id immediately.

Hibernate persist is similar to save with transaction. I feel it’s better than save because we can’t use it outside the boundary of transaction, so all the object mappings are preserved. Also persist doesn’t return the generated id immediately, so data persistence happens when needed.

Hibernate saveOrUpdate results into insert or update queries based on the provided data. If the data is present in the database, update query is executed. We can use saveOrUpdate() without transaction also, but again you will face the issues with mapped objects not getting saved if session is not flushed. For example usage of these methods, read Hibernate save vs persist.


What will happen if we don’t have no-args constructor in Entity bean?

Hibernate uses Reflection API to create instance of Entity beans, usually when you call get() or load() methods. The method Class.newInstance() is used for this and it requires no-args constructor. So if you won’t have no-args constructor in entity beans, hibernate will fail to instantiate it and you will get HibernateException.


What is difference between sorted collection and ordered collection, which one is better?

When we use Collection API sorting algorithms to sort a collection, it’s called sorted list. For small collections, it’s not much of an overhead but for larger collections it can lead to slow performance and OutOfMemory errors. Also the entity beans should implement Comparable or Comparator interface for it to work, read more at java object list sorting.

If we are using Hibernate framework to load collection data from database, we can use it’s Criteria API to use “order by” clause to get ordered list. Below code snippet shows you how to get it.

List<Employee> empList = session.createCriteria(Employee.class)
						.addOrder(Order.desc("id")).list();
Ordered list is better than sorted list because the actual sorting is done at database level, that is fast and doesn’t cause memory issues.


What are the collection types in Hibernate?

There are five collection types in hibernate used for one-to-many relationship mappings.

Bag
Set
List
Array
Map

How to implement Joins in Hibernate?

There are various ways to implement joins in hibernate.

Using associations such as one-to-one, one-to-many etc.
Using JOIN in the HQL query. There is another form “join fetch” to load associated data simultaneously, no lazy loading.
We can fire native sql query and use join keyword.

Why we should not make Entity Class final?

Hibernate use proxy classes for lazy loading of data, only when it’s needed. This is done by extending the entity bean, if the entity bean will be final then lazy loading will not be possible, hence low performance.


What is HQL and what are it’s benefits?

Hibernate Framework comes with a powerful object-oriented query language – Hibernate Query Language (HQL). It’s very similar to SQL except that we use Objects instead of table names, that makes it more close to object oriented programming.

Hibernate query language is case-insensitive except for java class and variable names. So SeLeCT is the same as sELEct is the same as SELECT, but com.journaldev.model.Employee is not same as com.journaldev.model.EMPLOYEE.

The HQL queries are cached but we should avoid it as much as possible, otherwise we will have to take care of associations. However it’s a better choice than native sql query because of Object-Oriented approach. Read more at HQL Example.


What is Query Cache in Hibernate?

Hibernate implements a cache region for queries resultset that integrates closely with the hibernate second-level cache.

This is an optional feature and requires additional steps in code. This is only useful for queries that are run frequently with the same parameters. First of all we need to configure below property in hibernate configuration file.

<property name="hibernate.cache.use_query_cache">true</property>
And in code, we need to use setCacheable(true) method of Query, quick example looks like below.

Query query = session.createQuery("from Employee");
query.setCacheable(true);
query.setCacheRegion("ALL_EMP");

Can we execute native sql query in hibernate?

Hibernate provide option to execute native SQL queries through the use of SQLQuery object.

For normal scenarios, it is however not the recommended approach because we loose benefits related to hibernate association and hibernate first level caching. Read more at Hibernate Native SQL Query Example.


What is the benefit of native sql query support in hibernate?

Native SQL Query comes handy when we want to execute database specific queries that are not supported by Hibernate API such as query hints or the CONNECT keyword in Oracle Database.


What is Named SQL Query?

Hibernate provides Named Query that we can define at a central location and use them anywhere in the code. We can created named queries for both HQL and Native SQL.

Hibernate Named Queries can be defined in Hibernate mapping files or through the use of JPA annotations @NamedQuery and @NamedNativeQuery.


What are the benefits of Named SQL Query?

Hibernate Named Query helps us in grouping queries at a central location rather than letting them scattered all over the code.
Hibernate Named Query syntax is checked when the hibernate session factory is created, thus making the application fail fast in case of any error in the named queries.
Hibernate Named Query is global, means once defined it can be used throughout the application.

However one of the major disadvantage of Named query is that it’s hard to debug, because we need to find out the location where it’s defined.


What is the benefit of Hibernate Criteria API?

Hibernate provides Criteria API that is more object oriented for querying the database and getting results. We can’t use Criteria to run update or delete queries or any DDL statements. It’s only used to fetch the results from the database using more object oriented approach.

Some of the common usage of Criteria API are:

Criteria API provides Projection that we can use for aggregate functions such as sum(), min(), max() etc.
Criteria API can be used with ProjectionList to fetch selected columns only.
Criteria API can be used for join queries by joining multiple tables, useful methods are createAlias(), setFetchMode() and setProjection()
Criteria API can be used for fetching results with conditions, useful methods are add() where we can add Restrictions.
Criteria API provides addOrder() method that we can use for ordering the results.
Learn some quick examples at Hibernate Criteria Example.


How to log hibernate generated sql queries in log files?

We can set below property for hibernate configuration to log SQL queries.

        <property name="hibernate.show_sql">true</property>
However we should use it only in Development or Testing environment and turn it off in production environment.


What is Hibernate Proxy and how it helps in lazy loading?

Hibernate uses proxy object to support lazy loading. Basically when you load data from tables, hibernate doesn’t load all the mapped objects. As soon as you reference a child or lookup object via getter methods, if the linked entity is not in the session cache, then the proxy code will go to the database and load the linked object. It uses javassist to effectively and dynamically generate sub-classed implementations of your entity objects.


How to implement relationships in hibernate?

We can easily implement one-to-one, one-to-many and many-to-many relationships in hibernate. It can be done using JPA annotations as well as XML based configurations. For better understanding, you should go through following tutorials.

Hibernate One to One Mapping
Hibernate One to Many Mapping
Hibernate Many to Many Mapping

How transaction management works in Hibernate?

Transaction management is very easy in hibernate because most of the operations are not permitted outside of a transaction. So after getting the session from SessionFactory, we can call session beginTransaction() to start the transaction. This method returns the Transaction reference that we can use later on to either commit or rollback the transaction.

Overall hibernate transaction management is better than JDBC transaction management because we don’t need to rely on exceptions for rollback. Any exception thrown by session methods automatically rollback the transaction.


What is cascading and what are different types of cascading?

When we have relationship between entities, then we need to define how the different operations will affect the other entity. This is done by cascading and there are different types of it.

Here is a simple example of applying cascading between primary and secondary entities.

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {

@OneToOne(mappedBy = "employee")
@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
private Address address;

}
Note that Hibernate CascadeType enum constants are little bit different from JPA javax.persistence.CascadeType, so we need to use the Hibernate CascadeType and Cascade annotations for mappings, as shown in above example.
Commonly used cascading types as defined in CascadeType enum are:

None: No Cascading, it’s not a type but when we don’t define any cascading then no operations in parent affects the child.
ALL: Cascades save, delete, update, evict, lock, replicate, merge, persist. Basically everything
SAVE_UPDATE: Cascades save and update, available only in hibernate.
DELETE: Corresponds to the Hibernate native DELETE action, only in hibernate.
DETATCH, MERGE, PERSIST, REFRESH and REMOVE – for similar operations
LOCK: Corresponds to the Hibernate native LOCK action.
REPLICATE: Corresponds to the Hibernate native REPLICATE action.

How to integrate log4j logging in hibernate application?

Hibernate 4 uses JBoss logging rather than slf4j used in earlier versions. For log4j configuration, we need to follow below steps.

Add log4j dependencies for maven project, if not maven then add corresponding jar files.
Create log4j.xml configuration file or log4j.properties file and keep it in the classpath. You can keep file name whatever you want because we will load it in next step.
For standalone projects, use static block to configure log4j using DOMConfigurator or PropertyConfigurator. For web applications, you can use ServletContextListener to configure it.
That’s it, our setup is ready. Create org.apache.log4j.Logger instance in the java classes and start logging. For complete example code, you should go through Hibernate log4j example and Servlet log4j example.


How to use application server JNDI DataSource with Hibernate framework?

For web applications, it’s always best to allow servlet container to manage the connection pool. That’s why we define JNDI resource for DataSource and we can use it in the web application. It’s very easy to use in Hibernate, all we need is to remove all the database specific properties and use below property to provide the JNDI DataSource name.

<property name="hibernate.connection.datasource">java:comp/env/jdbc/MyLocalDB</property>
For a complete example, go through Hibernate JNDI DataSource Example.


How to integrate Hibernate and Spring frameworks?

Spring is one of the most used Java EE Framework and Hibernate is the most popular ORM framework. That’s why Spring Hibernate combination is used a lot in enterprise applications. The best part with using Spring is that it provides out-of-box integration support for Hibernate with Spring ORM module. Following steps are required to integrate Spring and Hibernate frameworks together.

Add hibernate-entitymanager, hibernate-core and spring-orm dependencies.
Create Model classes and corresponding DAO implementations for database operations. Note that DAO classes will use SessionFactory that will be injected by Spring Bean configuration.
If you are using Hibernate 3, you need to configure org.springframework.orm.hibernate3.LocalSessionFactoryBean or org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean in Spring Bean configuration file. For Hibernate 4, there is single class org.springframework.orm.hibernate4.LocalSessionFactoryBean that should be configured.
Note that we don’t need to use Hibernate Transaction Management, we can leave it to Spring declarative transaction management using @Transactional annotation.
For complete example go through Spring Hibernate Integration and Spring MVC Hibernate Integration.


What is HibernateTemplate class?

When Spring and Hibernate integration started, Spring ORM provided two helper classes – HibernateDaoSupport and HibernateTemplate. The reason to use them was to get the Session from Hibernate and get the benefit of Spring transaction management. However from Hibernate 3.0.1, we can use SessionFactory getCurrentSession() method to get the current session and use it to get the spring transaction management benefits. If you go through above examples, you will see how easy it is and that’s why we should not use these classes anymore.

One other benefit of HibernateTemplate was exception translation but that can be achieved easily by using @Repository annotation with service classes, shown in above spring mvc example. This is a trick question to judge your knowledge and whether you are aware of recent developments or not.


How to integrate Hibernate with Servlet or Struts2 web applications?

Hibernate integration with Servlet or Struts2 needs to be done using ServletContextListener, a complete example can be found at Hibernate Struts2 Integration Example.


Which design patterns are used in Hibernate framework?

Some of the design patterns used in Hibernate Framework are:

Domain Model Pattern – An object model of the domain that incorporates both behavior and data.
Data Mapper – A layer of Mappers that moves data between objects and a database while keeping them independent of each other and the mapper itself.
Proxy Pattern for lazy loading
Factory pattern in SessionFactory

What are best practices to follow with Hibernate framework?

Some of the best practices to follow in Hibernate are:

Always check the primary key field access, if it’s generated at the database layer then you should not have a setter for this.
By default hibernate set the field values directly, without using setters. So if you want hibernate to use setters, then make sure proper access is defined as @Access(value=AccessType.PROPERTY).
If access type is property, make sure annotations are used with getter methods and not setter methods. Avoid mixing of using annotations on both filed and getter methods.
Use native sql query only when it can’t be done using HQL, such as using database specific feature.
If you have to sort the collection, use ordered list rather than sorting it using Collection API.
Use named queries wisely, keep it at a single place for easy debugging. Use them for commonly used queries only. For entity specific query, you can keep them in the entity bean itself.
For web applications, always try to use JNDI DataSource rather than configuring to create connection in hibernate.
Avoid Many-to-Many relationships, it can be easily implemented using bidirectional One-to-Many and Many-to-One relationships.
For collections, try to use Lists, maps and sets. Avoid array because you don’t get benefit of lazy loading.
Do not treat exceptions as recoverable, roll back the Transaction and close the Session. If you do not do this, Hibernate cannot guarantee that in-memory state accurately represents the persistent state.
Prefer DAO pattern for exposing the different methods that can be used with entity bean
Prefer lazy fetching for associations

What is Hibernate Validator Framework?

Data validation is integral part of any application. You will find data validation at presentation layer with the use of Javascript, then at the server side code before processing it. Also data validation occurs before persisting it, to make sure it follows the correct format.

Validation is a cross cutting task, so we should try to keep it apart from our business logic. That’s why JSR303 and JSR349 provides specification for validating a bean by using annotations. Hibernate Validator provides the reference implementation of both these bean validation specs. Read more at 