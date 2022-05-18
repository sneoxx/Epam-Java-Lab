package com.zaraev.epam.javacourses.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/customercsv")
public interface CustomerCsvResource {

    @GetMapping("/export")
    void export(HttpServletResponse response) throws Exception;
}
