SELECT * FROM products WHERE title = 'ACADEMY ACADEMY';

SELECT * FROM products WHERE price = 9.99 and category = 8 ORDER BY category, price;

SELECT * FROM products WHERE category in (8,15);

SELECT * FROM products WHERE price BETWEEN 10 AND 20;

SELECT * FROM orders WHERE orderdate BETWEEN to_date('2004-01-05', 'yyyy/MM/dd') AND to_date('2004-02-05', 'yyyy/MM/dd');

SELECT customerid,COUNT(*) FROM orders GROUP BY customerid;

SELECT customerid,orderdate,SUM(totalamount) FROM orders GROUP BY customerid, orderdate HAVING SUM(totalamount) > 100 ORDER BY SUM(totalamount);

SELECT c.firstname, c.lastname, o.orderdate, p.title 
FROM customers c 
JOIN orders o 
ON c.customerid = o.customerid
JOIN orderlines o2 
ON o2.orderid = o.orderid
JOIN products p
ON p.prod_id = o2.prod_id;