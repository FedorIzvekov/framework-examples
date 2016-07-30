package com.fedorizvekov.http.server.servlet;

import java.io.InputStream;
import java.util.EnumSet;
import java.util.Properties;
import javax.servlet.DispatcherType;
import com.fedorizvekov.http.server.servlet.filter.CorsFilter;
import com.fedorizvekov.http.server.servlet.servlet.RegistrationServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HttpServerServlet {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        }

        int port = Integer.parseInt(properties.getProperty("server.port"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new RegistrationServlet()), "/registration");
        context.addFilter(new FilterHolder(new CorsFilter()), "/*", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(port);
        server.setHandler(context);
        server.start();
        server.join();
    }

}
