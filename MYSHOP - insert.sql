USE myshop;

-- INSERT INTO Address(Country,City,Street,BlockNumber,Location,Phone,PostalCode) Values ('Iran','Mashhad','Bahonar',12, '24.5204905,-66.4534352,3z' , '8613890','91336');
-- INSERT INTO Address(Country,City,Street,BlockNumber,Location,Phone,PostalCode) Values ('Iran','Tehran','Valiasr',54, '15.6160034,35.9391429,4z' , '6712436','13566');
-- INSERT INTO Address(Country,City,Street,BlockNumber,Location,Phone,PostalCode) Values ('Iran','Mashhad','Sajad',8, '15.6160034,35.9391429,4z' , '7892158','54793');
-- INSERT INTO Address(Country,City,Street,BlockNumber,Location,Phone,PostalCode) Values ('Iran','Tabriz','Abbasi',3, '15.6160034,35.9391429,4z','78542','75412');
-- INSERT INTO Address(Country,City,Street,BlockNumber,Location,Phone,PostalCode) Values ('Iran','Esfehan','Ferdosi',25, '15.6160034,35.9391429,4z' , '9612745','45692');
-- INSERT INTO Address(Country,City,Street,BlockNumber,Location,Phone,PostalCode) Values ('Iran','Mashhad','Ahmadabad',35, '15.6160034,35.9391429,4z' , '4023890','36142');

-- DELETE from Address where ID=5;

-- INSERT INTO SHOP(ManagerName, AddressID,Email) Values ('Ali Hasani',1,'ahasani@gmail.com');

-- INSERT INTO CustomerProfile(Username,UserPassword,Email) Values ('negin.jafari',1234,'neg.jafari@gmail.com');
-- INSERT INTO CustomerProfile(Username,UserPassword,Email) Values ('ghazal.nozadi',1234,'ghazal.nozadi@gmail.com');

-- INSERT INTO Customer(FullName,BirthDate,AddressID, ProfileID, ShopID) values ('Negin Jafari','2000-05-11',2,1,1);
-- INSERT INTO Customer(FullName,BirthDate,AddressID, ProfileID, ShopID) values ('Ghazal Nozadi','2001-08-11',6,2,1);

-- INSERT INTO Provider(PName,Email,ShopID,AddressID) values ('Mahya Ehsanimehr','mahya,ehsani@gmail.com',1,7);

-- INSERT INTO staff(Sname,Salary,BirthDate,Email,AddressID,ManagerID,ShopID) values ('Ali hasani',50000000,'1980-11-12','ahasani@gmail.com',3,1,1);
-- INSERT INTO staff(Sname,Salary,BirthDate,Email,AddressID,ManagerID,ShopID) values ('Bita Karvizi',40000000,'2000-10-2','bita.karvizi@gmail.com',8,1,1);
-- INSERT INTO staff(Sname,Salary,BirthDate,Email,AddressID,ManagerID,ShopID) values ('Ali Davodi',40000000,'2000-07-20','ali.davodi@gmail.com',8,1,1);

-- INSERT INTO category(CategoryName) Values('Kitchen');
-- INSERT INTO category(CategoryName) Values('LivingRoom');

-- INSERT INTO Product(Price,Brand,ProductName,CategoryID,ShopID) values (200000,'yas','Mug',1,1);
-- INSERT INTO Product(Price,Brand,ProductName,CategoryID,ShopID) values (8000000,'LG','Microwave',1,1);
-- INSERT INTO Product(Price,Brand,ProductName,CategoryID,ShopID) values (5000000,'iranfarsh','Rug',2,1);
-- INSERT INTO Product(Price,Brand,ProductName,CategoryID,ShopID) values (20000000,'Samsung','TV',2,1);

-- UPDATE Product SET Brand="Yas" where ID=1;
