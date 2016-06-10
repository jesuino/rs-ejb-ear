RS in an EAR with EJB and Security
--

In this project you will find the following:    
* A JAX-RS resource on a WAR* The EJB definition on a JAR* The EJB implementation in another WAR

The JAX-RS resource will invoke the EJB. And it has security:    
* Method hello is allowed for any authenticated user;* Method helloRole1 is only for logged users that has the role1 role;* Method helloRole2 is only for logged users that has the role2 role;
## Building and testing the application

This app uses the security domain other, already available in JBoss EAP; To use it, create uses using the add-user script.

You can test the application adding a few users. These are the users I added in my application:
* No logged user: All the requests will return a 401 HTTP error:    
~~~
$ curl -u someNoUserThatDoesNotExist:somePassword http://localhost:8080/rs-lookup-security-web/hello/public
<html><head><title>JBoss Web/7.5.7.Final-redhat-1 - JBWEB000064: Error report</title><style><!--H1 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:22px;} H2 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:16px;} H3 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:14px;} BODY {font-family:Tahoma,Arial,sans-serif;color:black;background-color:white;} B {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;} P {font-family:Tahoma,Arial,sans-serif;background:white;color:black;font-size:12px;}A {color : black;}A.name {color : black;}HR {color : #525D76;}--></style> </head><body><h1>JBWEB000065: HTTP Status 401 - </h1><HR size="1" noshade="noshade"><p><b>JBWEB000309: type</b> JBWEB000067: Status report</p><p><b>JBWEB000068: message</b> <u></u></p><p><b>JBWEB000069: description</b> <u>JBWEB000121: This request requires HTTP authentication.</u></p><HR size="1" noshade="noshade"><h3>JBoss Web/7.5.7.Final-redhat-1</h3></body></html>
~~~
* usrNoRole - no role. All the requests will return a  `403 Not Authorized` error:~~~
$ curl -u usrNoRole:redhat2014! http://localhost:8080/rs-lookup-security-web/hello/public
<html><head><title>JBoss Web/7.5.7.Final-redhat-1 - JBWEB000064: Error report</title><style><!--H1 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:22px;} H2 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:16px;} H3 {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;font-size:14px;} BODY {font-family:Tahoma,Arial,sans-serif;color:black;background-color:white;} B {font-family:Tahoma,Arial,sans-serif;color:white;background-color:#525D76;} P {font-family:Tahoma,Arial,sans-serif;background:white;color:black;font-size:12px;}A {color : black;}A.name {color : black;}HR {color : #525D76;}--></style> </head><body><h1>JBWEB000065: HTTP Status 403 - JBWEB000015: Access to the requested resource has been denied</h1><HR size="1" noshade="noshade"><p><b>JBWEB000309: type</b> JBWEB000067: Status report</p><p><b>JBWEB000068: message</b> <u>JBWEB000015: Access to the requested resource has been denied</u></p><p><b>JBWEB000069: description</b> <u>JBWEB000123: Access to the specified resource has been forbidden.</u></p><HR size="1" noshade="noshade"><h3>JBoss Web/7.5.7.Final-redhat-1</h3></body></html>
~~~
* usrRole1: should be able to access hello, helloRole1 and have a 403 hen trying to access helloRole2:~~~
$ curl -u usrRole1:redhat2014! http://localhost:8080/rs-lookup-security-web/hello/public?name=William
Hello William, EJB from a JAX-RS resource. (this is an EJB in a WAR)

$ curl -u usrRole1:redhat2014! http://localhost:8080/rs-lookup-security-web/hello/role1?name=William
Hello William, EJB from a JAX-RS resource. (this is an EJB in a WAR)
~~~

User with role2 will be able to access helloRole1 and user with both roles will have access to all methods.

