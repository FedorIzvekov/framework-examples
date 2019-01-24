package com.fedorizvekov.http.server.servlet.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedorizvekov.http.server.servlet.model.UserDto;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/users")
public class UserServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        log.info("REQUEST POST endpoint {}", request.getRequestURL().toString());

        try {
            var json = request.getReader().readLine();
            var userDto = new ObjectMapper().readValue(json, UserDto.class);

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(userDto.toString());

            log.info("STATUS CODE: {}", HttpServletResponse.SC_CREATED);
            log.info("RESPONSE BODY: {}", userDto.toString());

        } catch (JsonProcessingException exception) {

            log.error("Bad request, because: ", exception);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        } catch (Exception exception) {

            log.error("Something went wrong, because: ", exception);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }

    }

}
