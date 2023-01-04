use myshop;

create table if not exists provider_product(
ProductID INT not null,
ProviderID INT not null,
Price int not null,
foreign key(ProductID)
references product(ID)
on delete cascade
on update cascade,
foreign key(providerID)
references provider(ID)
on delete cascade
on update cascade
)engine=innodb;