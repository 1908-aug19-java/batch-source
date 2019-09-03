create table Usuario(
	UserId serial constraint UserId primary key, 
	ClientName varchar(50),
	UserName varchar(50),
	UserAddress varchar(50),
	email varchar(50),
	pasword varchar(50) 
	);	
insert into usuario values (0,' ',' ',' ',' ',' ');
insert into usuario values (1,'Felipe','felo','2541 j ct','felipe@yahoo.es','asgtslkj');
insert into usuario values (2,'Julieta','juli','4444 k ct','juli@yahoo.es','asdlkasjdias');
insert into usuario values (3,'Romeo','rom_eo','5631 y ct','rom@yahoo.es','cbjasda');
insert into usuario values (4,'Gustavo','gusFring','4589 p ct','gusfring@lospolloshermanos.com','dsakjdhwad');


create table cuentas(
	cuentaId serial ,
	userId serial references usuario,
	tipo integer,
	userName  varchar(50),
	accountNumber integer constraint accountNumber primary Key,
	totalMoney numeric(9,2)
	);

insert into cuentas values (0,0,0,' ',0000,0.00);
insert into cuentas values (1,2,2,' ',1000,600.00);
insert into cuentas values (2,2,1,' ',2000,50.00);
insert into cuentas values (3,1,1,' ',3000,900.00);
insert into cuentas values (4,1,3,' ',2010,60.00);
insert into cuentas values (5,2,1,' ',2015,100.00);


create or replace function addUser(ClientName usuario.clientname%type,
									UserName usuario.username%type,
									UserAddress usuario.UserAddress%type,
									email usuario.pasword%type,
									pasword usuario.pasword%type)
returns setof "usuario"
language plpgsql
as $$
declare 
	max_id integer;
begin
	select max(UserId) into max_id
	from usuario;
	insert into usuario values ((max_id+1), ClientName, UserName, UserAddress, email, pasword);
	return query select * from usuario where UserId =(max_id+1);
end
$$;



create or replace function addAccount(									
									userIda cuentas.userid%type,
									tipoa   cuentas.tipo%type,
									userNamea  varchar(50),
									accountNumbera cuentas.accountnumber%type,
									totalMoneya cuentas.totalmoney%type							
									)
returns setof "cuentas"
language plpgsql
as $$
declare 
	max_id integer;
begin
	select max(cuentaid) into max_id
	from "cuentas";
	insert into "cuentas" values ((max_id+1), userIda, tipoa, userNamea, accountNumbera, totalMoneya);
	return query select * from "cuentas" where cuentaId =(max_id+1);
end
$$;





