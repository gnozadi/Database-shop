use myshop;

-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Yazd', 'Kashani', 10,'15.6160034,35.9391429,4z',6712436,13566);
-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Mashhad', 'Farhad', 5,'15.781368,35.8452369,4z',4254565,75486);
-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Shiraz', 'Zand', 7,'15.6478234,35.7523468,4z',1025463,96325);
-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Abadan', 'Amiri', 1,'15.6160034,35.9391429,4z',7845123,78523);
-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Tehran', 'Enghelab', 15,'15.6160034,35.9391429,4z',6712436,13566);
-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Ghom', 'Azar', 9,'15.6160034,35.9391429,4z',6712436,13566);
-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Karaj', 'Aban', 4,'15.6160034,35.9391429,4z',6712436,13566);
-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Rasht', 'Takhti', 11,'15.6160034,35.9391429,4z',6712436,13566);
-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Saari', 'Farhang', 16,'15.6160034,35.9391429,4z',6712436,13566);
-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Babol', 'Beheshti', 11,'15.6160034,35.9391429,4z',6712436,13566);
-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Sanandaj', 'Ferdowsi', 3,'15.6160034,35.9391429,4z',6712436,13566);
-- insert into address(country, city, street, blockNumber, Location, Phone,PostalCode) values('Iran', 'Kerman', 'Jahaad', 2,'15.6160034,35.9391429,4z',6712436,13566);

-- insert into category(CategoryName) values ('Bedroom');
-- insert into category(CategoryName) values ('Bathroom');


-- insert into customerprofile(Username, Userpassword, Email) values('Baraan Jamshidi', '1234','baran.jamshidi@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Paria Behnam', '1234','PariaBehanam@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Mostafa Moradi', '1234','Moradi.Mostafa@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Rahaa Alipoor', '1234','ralipoor@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Koorosh Samani', '1234','samani.koorosh@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Leila Hosseine', '1234','Leila.hosseini@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Parsa Jahani', '1234','Parsa.Jahani@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Mitra Salari', '1234','m.salari@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Reza Nasiri', '1234','r-nasiri@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Nazanin Alavi', '1234','Nazain.alavi@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Taha Pahlavan', '1234','t.pahlavan@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Reihane Samadi', '1234','r-samadi@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Raamin Rezazade', '1234','Ramin-rezazade@gmail.com');
-- insert into customerprofile(Username, Userpassword, Email) values('Elahe Hamidi', '1234','hamidi-elahe@gmail.com');

-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Ali Mohammadi', 21,1,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Baraan Jamshidi', 22,2,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Paria Behnam', 9,5,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Mostafa Moradi', 10,6,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Rahaa Alipoor', 11,7,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Koorosh Samani', 12,8,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Leila Hosseine', 13,9,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Parsa Jahani', 14,10,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Mitra Salari', 15,11,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Reza Nasiri', 16,12,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Nazanin Alavi', 17,13,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Taha Pahlavan', 17,14,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Reihane Samadi', 18,15,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Raamin Rezazade', 19,16,1);
-- insert into customer(FullName, AddressID, ProfileID,ShopID) values('Elahe Hamidi', 20,17,1);


-- insert into product (price, brand, productname, categoryid, shopid) values (23000000,'Bosch','Washing Machine', 1,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (4000000,'Pars Khazar','Vacuum Cleaner', 1,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (14000000,'Akhavan','Oven', 1,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (30000000,'Snowa','Fridge', 1,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (55000000,'Toranj','Sofa', 2,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (1400000,'Toranj','Table', 2,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (2000000,'Ikea','Shoe Cabinet', 2,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (140000,'Ikea','Wall Shelf', 2,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (290000,'Ikea','Floor Lamp', 2,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (4000000,'Ikea','Curtain', 2,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (1500000,'Khoshkhab','Matress', 3,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (260000,'Golbaft','Blanket', 3,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (100000,'Roya','Pillow', 3,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (4000000,'DND','Desk', 3,1);
-- insert into product (price, brand, productname, categoryid, shopid) values (1000000,'Ikea','Chair', 3,1);

-- insert into productreview(title, rating, published) value ("Not Bad",3,1);
insert into productreview(title, rating, published) value ("Awful",1,1);
insert into productreview(title, rating, published) value ("Perfect",5,1);
insert into productreview(title, rating, published) value ("Loved it!",4,1);
insert into productreview(title, rating, published) value ("Could have been better",2,1);
insert into productreview(title, rating, published) value ("Don't recommend",2,1);
insert into productreview(title, rating, published) value ("Good",4,1);
insert into productreview(title, rating, published) value ("Awsome",5,1);
insert into productreview(title, rating, published) value ("The best I've seen",5,1);
insert into productreview(title, rating, published) value ("Not Bad",3,1);