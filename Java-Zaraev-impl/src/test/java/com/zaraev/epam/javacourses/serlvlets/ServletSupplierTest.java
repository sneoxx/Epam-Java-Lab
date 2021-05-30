package com.zaraev.epam.javacourses.serlvlets;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

class ServletSupplierTest {

    @Test
    public void Dfdfdf(){
        String myJson = "{\n" +
                "\"orderId\": 1,\n" +
                "\"orderNumber\": \"10944+vkq\",\n" +
                "\"orderDate\": \"May 28, 2021, 3:56:18 PM\",\n" +
                "\"totalAmount\": 999.00,\n" +
                "\"customer\":{\n" +
                "\"customerId\": 3,\n" +
                "\"customerName\": \"qglx\",\n" +
                "\"phone\": \"7445\"\n" +
                "},\n" +
                "\"products\":[]\n" +
                "}";

        ServletSupplier servletSupplier = new ServletSupplier();
        HttpServletRequest reg;
//        System.out.println(reg);
//        servletSupplier.doGet();
    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        if (req.getParameterNames().hasMoreElements()) {
//            var id = SERVICE_SERVLETS.getIdFromRequest(req);
//            if (id != 0) {
//                Supplier supplier = SERVICE_ENTITY.getSupplier(id); // получаем объект Supplier по id
//                var jsonString = this.GSON.toJson(supplier); //преобразуем в json на основании полей объекта
//                SERVICE_SERVLETS.printJson(jsonString, resp);
//            }
//        } else {
//            var jsonString = GSON.toJson(SERVICE_ENTITY.getAllSupplier(), List.class);
//            SERVICE_SERVLETS.printJson(jsonString, resp);
//        }
//    }
}