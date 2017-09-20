DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `STOCK_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `STOCK_CODE` VARCHAR(10) NOT NULL,
  `STOCK_NAME` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`STOCK_ID`) USING BTREE,
  UNIQUE KEY `UNI_STOCK_NAME` (`STOCK_NAME`),
  UNIQUE KEY `UNI_STOCK_ID` (`STOCK_CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `stock_detail`;
CREATE TABLE  `stock_detail` (
 `STOCK_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
 `COMP_NAME` varchar(100) NOT NULL,
 `COMP_DESC` varchar(255) NOT NULL,
 `REMARK` varchar(255) NOT NULL,
 `LISTED_DATE` date NOT NULL,
 PRIMARY KEY (`STOCK_ID`) USING BTREE,
 CONSTRAINT `FK_STOCK_ID` FOREIGN KEY (`STOCK_ID`) REFERENCES `stock` (`STOCK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `stock_daily_record`;
CREATE TABLE  `stock_daily_record` (
  `RECORD_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `PRICE_OPEN` float(6,2) DEFAULT NULL,
  `PRICE_CLOSE` float(6,2) DEFAULT NULL,
  `PRICE_CHANGE` float(6,2) DEFAULT NULL,
  `VOLUME` bigint(20) unsigned DEFAULT NULL,
  `DATE` date NOT NULL,
  `STOCK_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`RECORD_ID`) USING BTREE,
  UNIQUE KEY `UNI_STOCK_DAILY_DATE` (`DATE`),
  KEY `FK_STOCK_TRANSACTION_STOCK_ID` (`STOCK_ID`),
  CONSTRAINT `FK_STOCK_TRANSACTION_STOCK_ID` FOREIGN KEY (`STOCK_ID`)
  REFERENCES `stock` (`STOCK_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `CATEGORY_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(10) NOT NULL,
  `DESC` varchar(255) NOT NULL,
  PRIMARY KEY (`CATEGORY_ID`) USING BTREE
)

DROP TABLE IF EXISTS `stock_category`;
CREATE TABLE  `stocks_category` (
  `STOCK_ID` int(10) unsigned NOT NULL,
  `CATEGORY_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`STOCK_ID`,`CATEGORY_ID`),
  CONSTRAINT `FK_CATEGORY_ID` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`CATEGORY_ID`),
  CONSTRAINT `FK_STOCKS_ID` FOREIGN KEY (`STOCK_ID`) REFERENCES `stock` (`STOCK_ID`)
)


DROP TABLE IF EXISTS `auditlog`;
CREATE TABLE `auditlog` (
  `AUDIT_LOG_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ACTION` varchar(100) NOT NULL,
  `DETAIL` text NOT NULL,
  `CREATED_DATE` date NOT NULL,
  `ENTITY_ID` bigint(20) unsigned NOT NULL,
  `ENTITY_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`AUDIT_LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

Hibernate – Cascade example (save, update, delete and delete-orphan)

Cascade is a convenient feature to save the lines of code needed to manage the state of the other side manually.
The “Cascade” keyword is often appear on the collection mapping to manage the state of the collection automatically. In this tutorials, this one-to-many example will be used to demonstrate the cascade effect.

Cascade save / update example
In this example, if a ‘Stock’ is saved, all its referenced ‘stockDailyRecords’ should be saved into database as well.


 
1. No save-update cascade
In previous section, if you want to save the ‘Stock’ and its referenced ‘StockDailyRecord’ into database, you need to save both individually.

Stock stock = new Stock();
StockDailyRecord stockDailyRecords = new StockDailyRecord();
//set the stock and stockDailyRecords  data

stockDailyRecords.setStock(stock);
stock.getStockDailyRecords().add(stockDailyRecords);

session.save(stock);
session.save(stockDailyRecords);
Output

Hibernate:
    insert into stock (STOCK_CODE, STOCK_NAME)
    values (?, ?)

Hibernate:
    insert into stock_daily_record
    (STOCK_ID, PRICE_OPEN, PRICE_CLOSE, PRICE_CHANGE, VOLUME, DATE)
    values (?, ?, ?, ?, ?, ?)

 
2. With save-update cascade
The cascade=”save-update” is declared in ‘stockDailyRecords’ to enable the save-update cascade effect.

<!-- Stock.hbm.xml -->
<set name="stockDailyRecords" cascade="save-update" table="stock_daily_record"...>
      <key>
            <column name="STOCK_ID" not-null="true" />
      </key>
      <one-to-many class="com.test.hibernate.tutorial.one2many.StockDailyRecord" />
</set>
Stock stock = new Stock();
StockDailyRecord stockDailyRecords = new StockDailyRecord();
//set the stock and stockDailyRecords  data

stockDailyRecords.setStock(stock);
stock.getStockDailyRecords().add(stockDailyRecords);

session.save(stock);
Output

Hibernate:
    insert into stock (STOCK_CODE, STOCK_NAME)
    values (?, ?)

Hibernate:
    insert into stock_daily_record
    (STOCK_ID, PRICE_OPEN, PRICE_CLOSE, PRICE_CHANGE, VOLUME, DATE)
    values (?, ?, ?, ?, ?, ?)
The code session.save(stockDailyRecords); is no longer required, when you save the ‘Stock’, it will “cascade” the save operation to it’s referenced ‘stockDailyRecords’ and save both into database automatically.

Cascade delete example
In this example, if a ‘Stock’ is deleted, all its referenced ‘stockDailyRecords’ should be deleted from database as well.

1. No delete cascade
You need to loop all the ‘stockDailyRecords’ and delete it one by one.

Query q = session.createQuery("from Stock where stockCode = :stockCode ");
q.setParameter("stockCode", "4715");
Stock stock = (Stock)q.list().get(0);

for (StockDailyRecord sdr : stock.getStockDailyRecords()){
         session.delete(sdr);
}
 session.delete(stock);
Output

Hibernate:
    delete from stock_daily_record
    where DAILY_RECORD_ID=?

Hibernate:
    delete from stock
    where STOCK_ID=?
2. With delete cascade
The cascade=”delete” is declared in ‘stockDailyRecords’ to enable the delete cascade effect. When you delete the ‘Stock’, all its reference ‘stockDailyRecords’ will be deleted automatically.

<!-- Stock.hbm.xml -->
<set name="stockDailyRecords" cascade="delete" table="stock_daily_record" ...>
      <key>
            <column name="STOCK_ID" not-null="true" />
      </key>
      <one-to-many class="com.test.hibernate.tutorial.one2many.StockDailyRecord" />
</set>
Query q = session.createQuery("from Stock where stockCode = :stockCode ");
q.setParameter("stockCode", "4715");
Stock stock = (Stock)q.list().get(0);
session.delete(stock);
Output

Hibernate:
    delete from stock_daily_record
    where DAILY_RECORD_ID=?

Hibernate:
    delete from stock
    where STOCK_ID=?
Cascade delete-orphan example
In above cascade delete option, if you delete a Stock , all its referenced ‘stockDailyRecords’ will be deleted from database as well. How about if you just want to delete two referenced ‘stockDailyRecords’ records? This is called orphan delete, see example…

1. No delete-orphan cascade
You need to delete the ‘stockDailyRecords’ one by one.

StockDailyRecord sdr1 = (StockDailyRecord)session.get(StockDailyRecord.class,
                                            new Integer(56));
StockDailyRecord sdr2 = (StockDailyRecord)session.get(StockDailyRecord.class,
                                            new Integer(57));

session.delete(sdr1);
session.delete(sdr2);
Output

Hibernate:
    delete from stock_daily_record
    where DAILY_RECORD_ID=?
Hibernate:
    delete from stock_daily_record
    where DAILY_RECORD_ID=?
2. With delete-orphan cascade
The cascade=”delete-orphan” is declared in ‘stockDailyRecords’ to enable the delete orphan cascade effect. When you save or update the Stock, it will remove those ‘stockDailyRecords’ which already mark as removed.

<!-- Stock.hbm.xml -->
<set name="stockDailyRecords" cascade="delete-orphan" table="stock_daily_record" >
      <key>
            <column name="STOCK_ID" not-null="true" />
      </key>
      <one-to-many class="com.test.hibernate.tutorial.one2many.StockDailyRecord" />
</set>
StockDailyRecord sdr1 = (StockDailyRecord)session.get(StockDailyRecord.class,
                                       new Integer(56));
StockDailyRecord sdr2 = (StockDailyRecord)session.get(StockDailyRecord.class,
                                       new Integer(57));

Stock stock = (Stock)session.get(Stock.class, new Integer(2));
stock.getStockDailyRecords().remove(sdr1);
stock.getStockDailyRecords().remove(sdr2);

session.saveOrUpdate(stock);
Output

Hibernate:
    delete from stock_daily_record
    where DAILY_RECORD_ID=?
Hibernate:
    delete from stock_daily_record
    where DAILY_RECORD_ID=?
In short, delete-orphan allow parent table to delete few records (delete orphan) in its child table.
How to enable cascade ?
The cascade is supported in both XML mapping file and annotation.

1. XML mapping file
In XML mapping file, declared the cascade keyword in your relationship variable.

<!-- Stock.hbm.xml -->
<set name="stockDailyRecords" cascade="save-update, delete"
        table="stock_daily_record" ...>
      <key>
            <column name="STOCK_ID" not-null="true" />
      </key>
      <one-to-many class="com.test.hibernate.tutorial.one2many.StockDailyRecord" />
</set>
2. Annotation
In annotation, declared the CascadeType.SAVE_UPDATE (save, update) and CascadeType.REMOVE (delete) in @Cascade annotation.

//Stock.java
        @OneToMany(mappedBy = "stock")
        @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
public Set<StockDailyRecord> getStockDailyRecords() {
	return this.stockDailyRecords;
}
Further study – Cascade – JPA & Hibernate annotation common mistake.

inverse vs cascade
Both are totally different notions, see the differential here.

Conclusion
Cascade is a very convenient feature to manage the state of the other side automatically. However this feature come with a price, if you do not use it wisely (update or delete), it will generate many unnecessary cascade effects (cascade update) to slow down your performance, or delete (cascade delete) some data you didn’t expected.


Different between cascade and inverse
By mkyong | February 5, 2010 | Updated : August 30, 2012 | Viewed : 147,226 times +353 pv/w

Many Hibernate developers are confuse about the cascade option and inverse keyword. In some ways..they really look quite similar at the beginning, both are related with relationship.

Cascade vs inverse
However, there is no relationship between cascade and inverse, both are totally different notions.


 
1. inverse
This is used to decide which side is the relationship owner to manage the relationship (insert or update of the foreign key column).

Example

In this example, the relationship owner is belong to stockDailyRecords (inverse=true).

<!-- Stock.hbm.xml -->
<hibernate-mapping>
    <class name="com.test.hibernate.tutorial.one2many.Stock" table="stock" ...>
    ...
    <set name="stockDailyRecords" table="stock_daily_record" inverse="true">
        <key>
            <column name="STOCK_ID" not-null="true" />
        </key>
        <one-to-many class="com.test.hibernate.tutorial.one2many.StockDailyRecord" />
    </set>
    ...
When you save or update the stock object

session.save(stock);
session.update(stock);
Hibernate will only insert or update the STOCK table, no update on the foreign key column. More detail example here…


 
2. cascade
In cascade, after one operation (save, update and delete) is done, it decide whether it need to call other operations (save, update and delete) on another entities which has relationship with each other.

Example

In this example, the cascade=”save-update” is declare on stockDailyRecords.

<!-- Stock.hbm.xml -->
<hibernate-mapping>
    <class name="com.test.hibernate.tutorial.one2many.Stock" table="stock" ...>
    ...
    <set name="stockDailyRecords" table="stock_daily_record"
        cascade="save-update" inverse="true">
        <key>
            <column name="STOCK_ID" not-null="true" />
        </key>
        <one-to-many class="com.test.hibernate.tutorial.one2many.StockDailyRecord" />
    </set>
    ...
When you save or update the stock object

session.save(stock);
session.update(stock);
It will inserted or updated the record into STOCK table and call another insert or update statement (cascade=”save-update”) on StockDailyRecord. More detail example here…

Conclusion
In short, the “inverse” is decide which side will update the foreign key, while “cascade” is decide what’s the follow by operation should execute. Both are look quite similar in relationship, but it’s totally two different things. Hibernate developers are worth to spend time to research on it, because misunderstand the concept or misuse it will bring serious performance or data integrity issue in your application.