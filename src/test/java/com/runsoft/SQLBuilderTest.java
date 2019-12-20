package com.runsoft;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static com.runsoft.SQLBuilder.*;

/** 
* SQLBuilder Tester. 
* 
* @author <Authors name> 
* @since <pre>12/16/2019</pre> 
* @version 1.0 
*/ 
public class SQLBuilderTest {

@Before
public void before() throws Exception { 

} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: NewBuilder(String table, String where) 
* 
*/ 
@Test
public void testNewBuilder() throws Exception { 
//TODO: Test goes here...
	DateTimeHelper.ConfigHelper(new DateTimeForChina());
	SQLBuilder.setTable("a", "sys_dict");
	SQLBuilder.TableAlias = true;

	defaultDatabaseType = DatabaseType.dtSQLServer;
	SQLBuilder builder = NewBuilder("a")
			.setDBType(DatabaseType.dtSQLServer)
			.setField("U_Name", "dmzn")
			.setField("U_Password", "mima")
			.setField("U_Group", 1, FieldType.sfVal)
			.setField("U_Mail", new Date(), FieldType.sfDateTime);
	System.out.println("Insert: " + builder.BuildInsert());

	System.out.println("Update: " +
			builder.BuildUpdate("U_Date <> #date# and U_Name=#name#", null));
}

	/**
* 
* Method: SetDBType(DatabaseType type) 
* 
*/ 
@Test
public void testSetDBType() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: AddField(String field, Object value, SQLFieldType type) 
* 
*/ 
@Test
public void testAddField() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: Build() 
* 
*/ 
@Test
public void testBuild() throws Exception { 
//TODO: Test goes here... 
} 


} 
