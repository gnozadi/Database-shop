use myshop;
-- order list -- 
select factor.ID, TotalPrice, TotalDiscount, 'Date' from factor, shoppingcart where FactorID = factor.ID;

-- suggested list -- 
select * from product where Discount>=15; 

-- 3 least review --
select ProductName, Brand, Price, product_review.Title, product_review.Rating 
from product, product_review
where productID = product.ID && ProductName = "Mug"
order by Rating limit 3


