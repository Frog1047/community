create table user_auths
(
	id int auto_increment,
	user_id int not null,
	identity_type VARCHAR(20) not null,
	identifier VARCHAR(50) not null,
	credential VARCHAR(50) not null,
	GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
	constraint user_auths_pk
		primary key (id)
);

alter table USER drop column ACCOUNT_ID;
