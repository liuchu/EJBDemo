#This is a simple EJB demo.

This demo is suitable for audience who are EJB3 beginner.

##Pre-suite
A windows computer with below software:
1. Application server, recommend wildfly-11.0.0.Final.
2. Git and git bash, recommend git version 2+.
3. Maven, recommend maven version 3+.
4. Java IDE, recommend Intellij. IDE is used to view the source code. 

You can checkout to different tag to evaluate different usage in different case.

EJB and client in same war, try `git checkout in_same_WAR`
EJB and client in same ear, but different war, try `git checkout in_same_ear_but_diff_war`
EJB and client in same container, but different war, try `git checkout in_same_container_but_diff_war`
EJB and client not in same container(Client has no container), try `git checkout in_diff_container`

##Begin
Note: This tutorial is based on Windows machine, some actions may be different for different device.    
Open the directory you'd like locate this repository,
Right click the blank space , click `git bash here`, type below command in the terminal:  
`git clone git@github.com:liuchu/EJBDemo.git`
`cd EJBDemo`

You have all the source code now, follow below different sections to evaluate different usage  

####EJB and client in same war
This scenario is EJB client and server code are in same war
```
    git checkout -b in_same_WAR 
    cd ej3-server-client-war
    mvn clean package
```
You will see a war named `ejb3-server-client-war.war` in `./target/`



