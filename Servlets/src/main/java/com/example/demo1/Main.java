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

        // 1️⃣ Set hostname to 0.0.0.0 before starting
        tomcat.getConnector(); // initializes default connector
        tomcat.getConnector().setProperty("address", "0.0.0.0");

        // 2️⃣ Context setup
        String contextPath = "";
        String docBase = new File("src/main/webapp").getAbsolutePath();
        Context context = tomcat.addContext(contextPath, docBase);

        context.addWelcomeFile("index.html");
        Tomcat.addDefaultMimeTypeMappings(context);

        // 3️⃣ Default servlet for static files
        Tomcat.addServlet(context, "default", new DefaultServlet());
        context.addServletMappingDecoded("/", "default");

        // 4️⃣ Add our DataServlet
        Tomcat.addServlet(context, "dataServlet", new DataServlet());
        context.addServletMappingDecoded("/data/*", "dataServlet");

        // 5️⃣ Start Tomcat
        tomcat.start();
        tomcat.getServer().await();
    }
}
