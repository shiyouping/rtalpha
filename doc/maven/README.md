![Maven Inheritance Hierarchy](https://github.com/shiyouping/rtalpha/blob/master/doc/maven/inheritance.png)

## 1. Parent POM
The pom of rtalpha-parent is the top level pom and all RT Alpha maven projects are inherited from it. As server applications are built on top of Spring, rtalpha-parent is also the child of spring-boot-starter-parent, so that it is not necessary to manage third party libraries commonly used in Spring ecosystem to avoid compatibility issue and conflicts of dependencies. As a convention, dependencies, properties, plugins, etc, which are shared in all RT Alpha maven projects, are defined in the pom of rtalpha-parent. 
(To Be Continued)