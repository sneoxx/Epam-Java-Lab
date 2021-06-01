package com.zaraev.epam.javacourses.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ServiceServlets {


    public void printJson(String jsonString, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonString);
        out.flush();
    }

    public String parseJsonToString(HttpServletRequest req) throws IOException {
        return req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public int getIdFromRequest(HttpServletRequest req) {
        if (req.getParameterNames().nextElement().equals("id") && !req.getParameter("id").equals("")) {
            return Integer.parseInt(req.getParameter("id"));
        }
        return 0;
    }

    public int validateAndGetIdFromRequest(HttpServletRequest req) {
        if (req.getParameterNames().hasMoreElements()) {
            if (req.getParameterNames().nextElement().equals("id") && !req.getParameter("id").equals("")) {
                return Integer.parseInt(req.getParameter("id"));
            }
//            if (id != 0) {
//                return true;
//            }
        }
        return 0;
    }
}