Coverage: 70.1%
# Project Title

This project is an Inventory Management System (IMS), which is used to create database for customers, items and orders. It is controlled vie command line interface. To create a customer you will need to give your first name and your surname and their id will be auto-incremented in the sql database. The items have a name, a make and how much they cost, hence price. The orders have an existing customer and a list of items they will order. This project has DAO classes, which interact with the sql database and it has Controller classes which display the infromation to the person who interacts with the project.  

## Getting Started

T1. Fork the project to your Github.
2. Make sure you have Maven installed
3. Make sure you have a Java Development Environment installed
4. Make sure you have Mysql workbench installed
5. Open the file using your java development environment

### Prerequisites

1.You need to install [Maven](http://maven.apache.org/download.cgi) version 3.6.3. This is used to build the final Jar file.


2. Make sure to install Java Development Kit [version 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html).It is used to run the project. Other version of JDK will end in failure  of opening the project. 

3. To create this project I used [Eclipse](https://www.eclipse.org/downloads/). Preferably use Eclipse but it should work on any java development environment program.
 
 
 4. I used [MySQLWorkBench](https://dev.mysql.com/downloads/workbench/5.2.html) to set up the SQL server that the project will manipulate. 
 



### Installing


Those softwares have executable installers and so they can be run

1. JDK 
I found this [website](https://javatutorial.net/set-java-home-windows-10) which will take you through the initial steps on how to get it working. 


2.Maven
I used this website on how to set up the [Maven](https://www.onlinetutorialspoint.com/maven/how-to-install-maven-on-windows10.html#:~:text=Go%20to%20Advanced%20System%20Settings%20%3A&text=Select%20the%20Path%20variable%20and,till%20%5Cbin)%20like%20below)


Then you can follow those [instructions](https://www.toolsqa.com/java/maven/how-to-install-maven-eclipse-ide/) to connect Maven to Eclipse. 


3.MYSQLWorkBench
Once you [install](https://www.toolsqa.com/java/maven/how-to-install-maven-eclipse-ide/) it you will have to create a new database server with the name of the project. instructions on how to do it are here 

 Once you have done that run the command 
 ##CREATE SCHEMA ims 
 once you you open it up.
 
 4. Opening the project in Eclipse
  Once you open up Eclipse, and go to file in the top menu, click on import , click on Maven and the Existing Maven Project.


##Using the project
 Once you run the project, you will be asked for username and password. Those are the once you used to set in the mySQLWorkbench. 
 When you did this you will come to a window that will ask you on which part you would like to work on.
 
 Which entity would you like to use?
 CUSTOMER: Information about customers
 ITEM: Individual Items
 ORDER: Purchases of items
 STOP: To close the application 
 
 If you type in customer. You will be given couple of option:
 
 What would you like to do with customer:
 CREATE: To save a new entity into the database
 READ: To read an entity from the database
 UPDATE: To change an entity already in the database
 DELETE: To remove an entity from the database
 RETURN: To return to domain selection
 
 If you type in create. You will be asked to provide name and surname of the customer you would like to create. 
 Please enter a first name
 hi
 Please enter a surname
 bye
 Customer created
 
 then if you type in read, you will get
 id:1 first name: hi last name: bye
 
 When you type update, you will be asked for the id of the customer you would like to update. 
 Please enter the id of the customer you would like to update
 1
 please enter firstname
 HI
 please enter last name
 BYEYE
 Customer Updated
 
 Then if you read it again you will get something like
 id:1 first name: HI last name: BYEYE
 
 If you type in delete, it will ask for the id of the customer you would like to delete and delete this customer from the database. 


## Running the tests

To run the tests, in the ims folder right click on the src/test/resources folder. Then hover over the Coverage and click Run as Junit tests. 
![names of image](./path/to/file.jpg)
![coverage](.\Documentation\coverage.png)

### Unit Tests 

The JUnit tests are mainly used to test the DAOs in the project. You put in dummy data, which is in the src/test/resources/sql-data.sql  in and see if when the method is run will give you equality in the answers. 

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(1L, "chrisps", "walkers",0.79));
		expected.add(new Item(2L,"chrispy", "walky",1));
		assertEquals(expected,itemdao.readAll());
	}


### Integration Tests 
Those tests check the connections between the different parts of the project and whether the outputs are correct. We used Mockito testing on the Controller classes. 

	@Test
	public void testCreate() {
		final String Itemname="hi";
		final String Itemmake="bye";
		final double price=0.79;
		final Item item = new Item(Itemname,Itemmake,price);
		
		when(utils.getString()).thenReturn(Itemname,Itemmake);
		when(utils.getDouble()).thenReturn(price);
		when(dao.create(item)).thenReturn(item);
		
		assertEquals(item, controller.create());

		verify(utils, times(1)).getDouble();
		verify(utils, times(2)).getString();
		verify(dao, times(1)).create(item);
	}

## Deployment

[Creating a Jar file using maven](https://javatutorial.net/how-to-create-java-jar-file-with-maven)

Find where the location of the IMS-Starter in your file explorer and copy the location. Then open command prompt and type

cd location of the file
then type mvn clean package.

This will tell maven to create the .jar file and it will run the tests and assemble the final file. 

Once this is completed there is going to be a new file in the IMS-Starter called target, which will have two jar files one with and one without the dependencies. 
## Built With
* [MySQLWorkBench](https://dev.mysql.com/downloads/workbench/5.2.html)
* [Eclipse](https://www.eclipse.org/downloads/)
* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors
* **Gabriela Yordanova** - *Finalised work* - [GYordanova](https://github.com/GYordanova)

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Piers Barber for helping with the thousand questions I had
* Aswene Sivaraj  for helpin with the other thousand questions

