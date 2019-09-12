create table department(
	dept_id serial constraint dept_id primary key,
	dept_name varchar(50),
	monthly_budget numeric(6,2)
);

drop table department;

alter table department
alter column monthly_budget type numeric(7,2);

insert into department (dept_name, monthly_budget) values ('HR', 5400);

delete from department
where dept_id=2;

create table employee(
	empl_id serial constraint empl_pk primary key,
	empl_name varchar(50),
	monthly_salary numeric(7,2),
	empl_position varchar(25),
	manager_id integer,
	dept_id integer references department(dept_id)
);


insert into employee (empl_name, monthly_salary, empl_position,
manager_id, dept_id) values ('Lola Jenkins', 4300, 'MKT_DIR', null,3);

insert into department (dept_name, monthly_budget) values ('Marketing', 9200);
insert into department (dept_name, monthly_budget) values ('IT', 8000);
insert into department (dept_name, monthly_budget) values ('Sales', 9200);

update department
set monthly_budget=8500
where dept_id=5;


/*
 * Populating Employee Table
 */

insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (3, 'Aleta Gasgarth', 4052, 'HR Director', null, 1);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (4, 'Ulrike Hector', 3823, 'HR Assist Dir', 3, 1);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (5, 'Charis Fike', 3930, 'MKT Rep', 2, 3);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (6, 'Jemie Duffin', 3484, 'MKT Rep', 2, 3);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (7, 'Mildrid Legerwood',  3863, 'IT Manager', null, 4);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (8, 'Huey Fasson',  2869, 'IT Assist Manager', 7, 4);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (9, 'Baxie Dalgleish',  3523, 'IT Rep', 8, 4);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (10, 'Giacomo Ren', 2863, 'IT Rep', 8, 4);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (11, 'Billi Bisset',  2430, 'Sales Director', null, 5);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (12, 'Kirby Burgoine',  2687, 'Sales Rep', 11, 5);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (13, 'Huey Cathee', 3434, 'HR Rep', 4, 1);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (14, 'Lenora Craister', 4224, 'HR Rep', 4, 1);

-- this is a single line comment

/*
 * Using DQL to write SQL Queries
 * 
 */

select *
from department
where monthly_budget>8000
order by monthly_budget desc;

select empl_name, dept_id
from employee
where dept_id=4 or dept_id=5;

select empl_name, dept_id
from employee
where dept_id in (4,5);

-- aliasing a column 
select dept_id as id, dept_name as department
from department;

select empl_name, monthly_salary, empl_position 
from employee
order by monthly_salary desc;

select empl_name, monthly_salary, empl_position 
from employee
where empl_name like 'Huey%';


/*
 * Using aggregate functions
 */
select count(empl_id)
from employee;

select count(empl_id) as "Employee Count", dept_id as "Respective Dept Id"
from employee
group by dept_id;

select round(avg(monthly_salary),2) as AVG_SAL, dept_id as DEPT
from employee
group by dept_id
order by AVG_SAL;

-- here we use both an aggregate (avg) and scalar (round) function

/*
 * Using subqueries
 * - We can nest queries within other queries  
 * 
 */

select *
from employee
where monthly_salary = 
	(select max(monthly_salary)
	from employee);

/*
 * Working with set operations 
 * 
 */

select *
from employee
where empl_id > 8
union
select *
from employee
where dept_id =4;

select *
from employee
where empl_id > 8
union all
select *
from employee
where dept_id =4;

select *
from employee
where empl_id > 8
intersect
select *
from employee
where dept_id =4;

select *
from employee
where empl_id > 8
except
select *
from employee
where dept_id =4;


/* 
 * working with joins
 */

/*
select [columns]
from [left table]
(full/left/right) join [right table]
on [join condition]
 */

insert into department values (2, 'training', 99999.99);
insert into employee (empl_id, empl_name, monthly_salary, empl_position, manager_id, dept_id) values (1, 'Steve Jobs', 9000, 'CEO', null, null);

-- inner join using column alias
select employee.empl_name as "name", department.dept_name as "department"
from employee
join department
on employee.dept_id = department.dept_id;

-- inner join using implicit column alias
select employee.empl_name "name", department.dept_name "department"
from employee
join department
on employee.dept_id = department.dept_id;

-- inner join using table alias
select e.empl_name, d.dept_name
from employee e
join department d 
on e.dept_id = d.dept_id
where d.dept_name='Marketing';

-- outer join 
select e.empl_name, d.dept_name
from employee e
full outer join department d
on e.dept_id = d.dept_id;

-- left join 
select e.empl_name, d.dept_name
from employee e
left join department d
on e.dept_id = d.dept_id;

-- right join 
select e.empl_name, d.dept_name
from employee e
right join department d
on e.dept_id = d.dept_id;

-- inner join on the same table (self join)
select e.empl_name "Employee", m.empl_name "Manager"
from employee e
join employee m 
on e.manager_id = m.empl_id;


