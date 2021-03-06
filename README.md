# Black Rook Small Jetty Bootstrap

Copyright (c) 2020 Black Rook Software.  
[https://github.com/BlackRookSoftware/Small-Jetty](https://github.com/BlackRookSoftware/Small-Jetty)

[Latest Release](https://github.com/BlackRookSoftware/Small-Jetty/releases/latest)    
[Online Javadoc](https://blackrooksoftware.github.io/Small/javadoc/Jetty)


### Required Libraries

[Black Rook Small 1.4.1+](https://blackrooksoftware.github.io/Small)  
[Jetty 9.4.X+](https://www.eclipse.org/jetty/)  
* Jetty Http
* Jetty Server
* Jetty Servlet
* Jetty Util

[Jetty Websocket Implementation 9.4.X+](https://www.eclipse.org/jetty/)  
* Jetty Websocket API
* Jetty Websocket Common
* Jetty Websocket Server
* Jetty Websocket Servlet
* Jetty Websocket Server Implementation

The above Jetty components are for building - you are better off pulling the full Jetty implementation for runtime.

### Required Java Modules

[java.xml](https://docs.oracle.com/en/java/javase/11/docs/api/java.xml/module-summary.html)  
* [java.base](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/module-summary.html)  


### Introduction

This library enables using Jetty as the server for Small applications.


### Why?

Needed a bootstrapper for Small embedded web applications. This one uses Jetty.


### Library

Contained in this release is a series of classes that facilitate executing a Small application
using Jetty as the server.


### Compiling with Ant

To download dependencies for this project, type (`build.properties` will also be altered/created):

	ant dependencies

To compile this library with Apache Ant, type:

	ant compile

To make Maven-compatible JARs of this library (placed in the *build/jar* directory), type:

	ant jar

To make Javadocs (placed in the *build/docs* directory):

	ant javadoc

To compile main and test code and run tests (if any):

	ant test

To make Zip archives of everything (main src/resources, bin, javadocs, placed in the *build/zip* directory):

	ant zip

To compile, JAR, test, and Zip up everything:

	ant release

To clean up everything:

	ant clean
	

### Javadocs

Online Javadocs can be found at: [https://blackrooksoftware.github.io/Small/javadoc/Jetty](https://blackrooksoftware.github.io/Small/javadoc/Jetty)

### Other

This program and the accompanying materials are made available under the 
terms of the LGPL v2.1 License which accompanies this distribution.

A copy of the LGPL v2.1 License should have been included in this release (LICENSE.txt).
If it was not, please contact us for a copy, or to notify us of a distribution
that has not included it. 
