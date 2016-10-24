# introsde-2016-assignment-1
#Introduction to Service Design and Engineering, University of Trento, Trento, Italy 


**Student Name:**  Uzoma Chukwudi Udochukwu 

**Matriculation number:**  [MAT. 189716]

**Assignment 01**: Reading/Writing objects to and from XML and JSON

This ReadMe file contains very important information about Assignment 01 of this course. It explains what it is all about and how the program can be run.

**ABOUT THE CODE**

>The code consist of 4 classes : HealthProfileReader.java, JAXBMarshaller.java, JAXBUnMarshaller.java and JSONMarshaller.java, a people.xml file with a list of 20 people, a people.json file which contains the JSON version of 3 people. There is a corresponding people.xsd file which contains the xml schema of the people.xml.

>For the entire project there is a corresponding build.xml and ivy.xml file. The ivy.xml file manages the dependencies of the projects and build.xml manages the compilation and execution of each task.

>All the java classes are in the scr folder (package: intorSDE). 

**FILES AND THEIR FUNCTIONS**

>-**ivy.xml** file of dependency manager with specified dependencies, helps to manage the dependencies.

>-**build.xml** contains JAXB XJC which will generate necessary java classes for marshalling, compiles and runs the project.

>-**people.xml** contains the list of 20 people healh profiles

>-**people.xsd** is the XML schema for people.xml and is used in the build.xml to generate java objects for marshalling.

>-**HealthProfileReader.java** uses Xpath and prints the querred info:

> A)gets weight and height of a person given his/her first and last name

> B)prints the health profile of a person with given id (id=0005) 

> C)prints the list of all people in people.xml

> D)prints the list of people with weight > 90kg

>-**JAXBMarshaller.java** creates 3 persons using java and marshals them into the generated people1.xml using JAXB marshalling.

>-**JAXBUnMarshaller.java** reads the list of people from people.xml and unmarshals them into java objects.

>-**JSONMarshaller.java** creates 3 persons using java and marshals them to JSON.

**RUNNING THE PROGRAM**
  
 > 1) CLONE THE REPOSITORY USING THE LINK

 >  > git clone https://github.com/ChukwudiUzoma/introsde-2016-assignment-1.git
   
  >2) OPEN TERMINAL/CMD AND ENTER THE ROOT DIRECTORY OF THE JUST CLONED FOLDER
  
 > >  cd PATH_TO_ROOT_FOLDER
  
  >3) RUN THE EVALUATOR SCRIPT WITH THE ANT COMMAND (runs everything using build.xml as target)
    
 > > ant execute.evaluate
  
  >TO RUN THE JAVA FILES SEPARATELY, CHANGE "ant execute.evaluate" TO "ant execute.NAME_OF_JAVA_CLASS"
  
 > FOR EXAMPLE ant execute.JAXBMarshaller FOR THE JAXBMARSHELLER CLASS
 
**TECHNOLOGIES USED**
>-Java

>-XML 

>-XPATH

>-XSD 

>-JAXB

>-JSON

>-Jackson
