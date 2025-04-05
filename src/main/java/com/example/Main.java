package com.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * This class launches the web application in an embedded Jetty container.
 * This is the entry point to your application. The Java command that is used for
 * launching should fire this main method.
 */

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String webAppDirLocation = "src/main/webapp/";

        // Jetty
        String webPuerto = System.getenv("PORT");
        if (webPuerto == null || webPuerto.isEmpty()) {
            webPuerto = "8081";
        }

        Server server = new Server(Integer.valueOf(webPuerto));
        WebAppContext root = new WebAppContext();
        root.setContextPath("/");
        root.setDescriptor(webAppDirLocation + "/WEB-INF/web.xml");
        root.setResourceBase(webAppDirLocation);
        PersistenceManager.getInstance().getEntityManagerFactory();

        root.setParentLoaderPriority(true);

        server.setHandler(root);
        server.start();
        server.join();
    }
}

