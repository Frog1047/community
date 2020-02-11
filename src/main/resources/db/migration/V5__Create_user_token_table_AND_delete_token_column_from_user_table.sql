create table login_token
(
	id int auto_increment,
	user_id int not null,
	token varchar(36) not null,
	expired DATETIME not null,
	status int default 0,
	GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
	constraint login_token_pk
		primary key (id)
);

create unique index login_token_token_uindex
	on login_token (token);

alter table USER drop column TOKEN;

