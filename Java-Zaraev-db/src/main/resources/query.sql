SELECT * FROM products WHERE title = 'ACADEMY ACADEMY';

SELECT * FROM products WHERE price = 9.99 and category = 8 ORDER BY category, price;

SELECT * FROM products WHERE category = 8 or category = 15;

SELECT * FROM products WHERE price BETWEEN 10 AND 20;

SELECT * FROM orders WHERE orderdate BETWEEN '2004-01-05' AND '2004-02-05';

SELECT COUNT(customerid),orders FROM orders GROUP BY customerid, orders;

SELECT SUM(totalamount),orderdate FROM orders GROUP BY customerid, orderdate HAVING SUM(totalamount) > 100 ORDER BY SUM(totalamount);

SELECT c.firstname, c.lastname, o.orderdate, p.title 
FROM customers c 
JOIN orders o 
ON c.customerid = o.customerid
JOIN orderlines o2 
ON o2.orderid = o.orderid
JOIN products p
ON p.prod_id = o2.prod_id;