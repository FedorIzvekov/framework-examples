package com.fedorizvekov.websocket.server.servlet;

import java.io.InputStream;
import java.util.Properties;
import com.fedorizvekov.websocket.server.servlet.servlet.WebsocketMessengerServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WebsocketServerServlet {

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        }

        int port = Integer.parseInt(properties.getProperty("server.port"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new WebsocketMessengerServlet()), "/messenger");

        Server server = new Server(port);
        server.setHandler(context);
        server.start();
        server.join();

    }

}
