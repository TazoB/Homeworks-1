import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import servlets.MessageServlet;
import servlets.UserServlet;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(8080);

        String webappDir = new File("src/main/webapp").getAbsolutePath();
        Context context = tomcat.addWebapp("", webappDir);

        Tomcat.addServlet(context, "userServlet", new UserServlet());
        context.addServletMappingDecoded("/user/*", "userServlet");

        Tomcat.addServlet(context, "messageServlet", new MessageServlet());
        context.addServletMappingDecoded("/message/*", "messageServlet");

        tomcat.start();
        tomcat.getConnector();
        tomcat.getServer().await();
    }
}
