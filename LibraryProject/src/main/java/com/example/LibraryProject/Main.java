package com.example.LibraryProject;

import com.example.LibraryProject.Database.DatabaseConnectionManager;
import com.example.LibraryProject.Servlets.BookServlet;
import com.example.LibraryProject.Servlets.BorrowServlet;
import com.example.LibraryProject.Servlets.MemberServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        DatabaseConnectionManager dbcm = DatabaseConnectionManager.getInstance();
        dbcm.initialize();

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8080);

        String contextPath = "";
        String docBase = new File("").getAbsolutePath();
        Context context = tomcat.addContext(contextPath, docBase);

        Tomcat.addServlet(context, "bookServlet", new BookServlet());
        context.addServletMappingDecoded("/books/*", "bookServlet");

        Tomcat.addServlet(context, "memberServlet", new MemberServlet());
        context.addServletMappingDecoded("/members/*", "memberServlet");

        Tomcat.addServlet(context, "borrowServlet", new BorrowServlet());
        context.addServletMappingDecoded("/borrow/*", "borrowServlet");

        tomcat.start();
        tomcat.getConnector();
        tomcat.getServer().await();
    }
}
