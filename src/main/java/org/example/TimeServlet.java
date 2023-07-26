package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
@WebServlet ("/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String timezoneParametr = req.getParameter("timezone");

        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        if (timezoneParametr != null && !timezoneParametr.isEmpty()) {
            if (isValidTimeZone(timezoneParametr)){
                timeZone = TimeZone.getTimeZone(timezoneParametr);
            }else {
                resp.sendError(HttpServletResponse.SC_ACCEPTED, "Invalid timezone");
                return;
            }
        }


        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(timeZone);
        String formattedDate = dateFormat.format(currentDate);

        String htmlResponce = "<html><body><h2>Current Time: " + formattedDate + "</h2></body></html>";


        resp.setContentType("text/html");
        resp.getWriter().write(htmlResponce);
    }
        private boolean isValidTimeZone(String timeZoneId) {
            String[] timeZones = TimeZone.getAvailableIDs();
            for (String tz : timeZones) {
                if (tz.equals(timeZoneId)) {
                    return true;
                }
            }
            return false;
        }

}
