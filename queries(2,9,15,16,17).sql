-- query no.2: show users list
select * from customer;


-- query no.9: show the most cheap selling provider for admin
select PName as ProviderName, ProductName, table1.Price as Price from provider, product, (
	select ProductID, ProviderID, min(Price) as Price
	from product_provider
	group by ProductID
    ) as table1
where provider.ID = table1.ProviderID and product.ID = table1.ProductID;

   
-- query no.15: show the average sale of the month for admin
select EXTRACT(month from TransactionDate) as month, avg(TotalPrice) as averageSale from transaction, factor
where transaction.FactorID = factor.ID
group by month
order by month asc;
-- end

-- query no.16: show the users from a city
select City, FullName as CustomerName from customer, address
where address.ID = customer.AddressID
order by City;

-- query no.17: show the providers from a city
select City, PName as ProviderName from provider, address
where address.ID = provider.AddressID
order by City;