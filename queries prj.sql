use myshop;

-- Select * from customer;
-- select * from product;
-- select * from category;


select * from customer as c, transaction as t where c.ID = t.customerID group by week(TransactionDate) order by count(c.id) desc;