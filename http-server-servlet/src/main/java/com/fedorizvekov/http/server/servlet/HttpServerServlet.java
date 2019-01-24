package com.fedorizvekov.http.server.servlet;

import com.fedorizvekov.http.server.servlet.service.impl.ServerServiceImpl;
import com.fedorizvekov.http.server.servlet.util.PropertiesUtil;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class HttpServerServlet {

    public static void main(String[] args) throws Exception {
        var properties = PropertiesUtil.loadProperties("config.properties");
        var port = Integer.parseInt(properties.getProperty("server.port"));

        var serverService = new ServerServiceImpl(new ServletContextHandler(ServletContextHandler.SESSIONS), new Server(port));
        serverService.runServer();
    }

}
