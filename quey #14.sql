use myshop;


select productID, ProductName, month(date), sum(ci.totalPrice) from product as p,cartitem as ci, shoppingcart as sc, factor as f where p.id=ProductID and ci.ShoppingCartID = sc.ID and sc.FactorID = f.ID group by p.ProductName, month(date);
