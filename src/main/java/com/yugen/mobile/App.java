package com.yugen.mobile;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

/**
 * Hello world!
 *
 */
public class App 
{
    static final int port = 9001;
    static final String SERVER_ROOT = Class.class.getResource("/webroot").getPath();
    static final String APP_ROOT = Class.class.getResource("/apps").getPath();

    public static void main(String[] args) throws Exception {
        // Start web server
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.setBaseDir(SERVER_ROOT);
        tomcat.getHost().setAutoDeploy(false);

        String contextPath = ""; // do not use "/"
        StandardContext context = new StandardContext();
        context.setPath(contextPath); // Set the context path for this Context.
        context.setDocBase(SERVER_ROOT); // Set the document root for this Context.
        context.addLifecycleListener(new Tomcat.FixContextListener());
        tomcat.getHost().addChild(context);

        /**
         *  Use DefaultServlet for fetching static resources ：
         *
         *      http://localhost:8080/static/js/hello.js
         */
        tomcat.addServlet(contextPath, "defaultServlet", new DefaultServlet());
        context.addServletMappingDecoded("/static/*", "defaultServlet");

        tomcat.addWebapp("/cloud",APP_ROOT+"/cloud");

        tomcat.start();
        tomcat.getServer().await();
    }
}
