package com.example.demo1;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8080);

        String contextPath = "";
        String docBase = new File("src/main/webapp").getAbsolutePath();
        System.out.println("DocBase: " + docBase);

        Context context = tomcat.addContext(contextPath, docBase);

        context.addWelcomeFile("index.html");
        Tomcat.addDefaultMimeTypeMappings(context);
        Tomcat.addServlet(context, "default", new DefaultServlet());

        context.addServletMappingDecoded("/", "default");
        Tomcat.addServlet(context, "helloServlet", new HelloServlet());
        context.addServletMappingDecoded("/hello-servlet", "helloServlet");

        tomcat.start();
        tomcat.getConnector();
        tomcat.getServer().await();
    }
}
