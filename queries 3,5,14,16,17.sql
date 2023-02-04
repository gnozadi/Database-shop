use myshop;

-- #3
select * from category;

-- #5
select fullname,week(TransactionDate) as week from customer as c, transaction as t where c.ID = t.customerID group by week(TransactionDate) order by count(c.id) desc limit 10;
select fullname,month(TransactionDate) as month from customer as c, transaction as t where c.ID = t.customerID group by month(TransactionDate) order by count(c.id) desc limit 10; 

-- #14
select productID, ProductName, month(date), sum(ci.totalPrice) from product as p,cartitem as ci, shoppingcart as sc, factor as f 
where p.id=ProductID and ci.ShoppingCartID = sc.ID and sc.FactorID = f.ID group by month(date), month(date) order by month(date) asc;

-- #16
select fullname, city from customer as c, address as a where a.ID = c.addressID group by a.city;
select pname, city from provider as p, address as a where a.ID = p.addressID group by a.city;