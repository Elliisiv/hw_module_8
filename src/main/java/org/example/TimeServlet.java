package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        String timeZone = parseUTC(req);

        String utc = ZonedDateTime.now(ZoneId.of(timeZone)).format(DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm:ss"))
                + " " + timeZone;

        resp.getWriter().write(utc);

        resp.getWriter().close();
    }

    private String parseUTC(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("timezone")) {
            return request.getParameter("timezone").replace(" ", "+");
        }
        return "UTC";
    }
}