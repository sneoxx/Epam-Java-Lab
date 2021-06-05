package com.zaraev.epam.javacourses.helper;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

/**
 * Класс с общими методами для классов Servlet
 */
@Component
public class ServletsHelper {

    /**
     * Вывод на экран json
     * @param jsonString - строка json для вывода
     * @param resp - Response сервлета
     * @throws IOException
     */
    public void printJson(String jsonString, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(jsonString);
        out.flush();
    }

    /**
     * Перевод Json в String
     * @param req Request сервлета
     * @return - вернет String
     * @throws IOException
     */
    public String parseJsonToString(HttpServletRequest req) throws IOException {
        return req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     * Получить id из Request сервлета
     * @param req - Request сервлета
     * @return - int id
     */
    public int getIdFromRequest(HttpServletRequest req) {
        if (req.getParameterNames().nextElement().equals("id") && !req.getParameter("id").equals("")) {
            return Integer.parseInt(req.getParameter("id"));
        }
        return 0;
    }

    /**
     * Валидация Request и получение id из Request сервлета
     * @param req - Request сервлета
     * @return - int id
     */
    public int validateAndGetIdFromRequest(HttpServletRequest req) {
        if (req.getParameterNames().hasMoreElements()) {
            if (req.getParameterNames().nextElement().equals("id") && !req.getParameter("id").equals("")) {
                return Integer.parseInt(req.getParameter("id"));
            }
        }
        return 0;
    }
}