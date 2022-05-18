package com.zaraev.epam.javacourses.repository;

import com.zaraev.epam.javacourses.domain.entity.Customer;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerCsvJDBC {

    public List<Customer> getAllCustomer() {
        DBWorker worker = new DBWorker();
        String query = "SELECT * FROM customer ORDER BY customer_id ASC";

        List<Customer> customerList = new ArrayList<>();
            try {
                Statement statement = worker.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(resultSet.getInt(1));
                    customer.setCustomerName(resultSet.getString(2));
                    customer.setPhone(resultSet.getString(3));
                    customerList.add(customer);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        return customerList;
    }
}
