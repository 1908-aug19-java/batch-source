-- Create Tables OLD TABLES NOT USED
Drop table employees;
insert into employees (empl_id,empName,email,pass,managerID)values (1,'amir','amir@gmail.com','1234',0);
CREATE TABLE employees (
    empl_id SERIAL PRIMARY KEY,
    empName VARCHAR(30) NOT NULL,
    email VARCHAR (30) NOT NULL,
    pass VARCHAR (30) NOT NULL,
    managerID INTEGER 
);
ALTER TABLE employees add UNIQUE (email);
truncate  table employees cascade;
truncate table manager cascade;
delete from employees where managerID=3;


drop table manager;
CREATE TABLE manager (
    empl_id SERIAL  ,
    empName VARCHAR(30) NOT NULL,
    email VARCHAR (30) NOT NULL,
    pass VARCHAR (30) NOT NULL,
    manager_id INTEGER PRIMARY KEY
);
insert into manager (empl_id,empName,email,pass,manager_id)values (3,'HR Manager','HRmanager@manager.com','manager1',3);

drop table REIMBURSMENTS;
truncate table reimbursments;
CREATE TABLE  REIMBURSMENTS  (
    reimnum_id SERIAL PRIMARY KEY,
    empl_id INTEGER  REFERENCES employees (empl_id),
    Description varchar(100) NOT NULL,
    amount INT NOT NULL,
    status VARCHAR (15) NOT NULL,
    manager_id INTEGER REFERENCES "manager" (manager_id)
);