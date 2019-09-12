/*
 * functions
 * 
 * create or replace function [function name]([param])
 * returns [type]
 * language [lang]
 * as $function$
 * declare
 *  -- additional variables
 * begin 
 *  -- function code
 * end
 * $function$
 */

create function myFunc()
returns integer 
language plpgsql
as $function$
declare
	quantity integer := 30;
begin
	return quantity;
end;
$function$

select myFunc();

-- parameterizing functions
create function returnMe(num integer)
returns integer 
language plpgsql
as $$
begin
	return num;
end;
$$

select returnMe(12);

create function add(num1 integer, num2 integer)
returns integer
language plpgsql
as $$
begin 
	return num1+num2;
end;
$$

select add(2,3);
-- we could also use these functions 
  -- in select statements with our tables
  
-- we can use functions to do ddl as well
create or replace function addDept(dpt_name department.dept_name%type, budget department.monthly_budget%type)
returns integer
language plpgsql
as $$
declare 
	max_id integer;
begin
	select max(dept_id) into max_id
	from department;
	insert into department values ((max_id+1), dpt_name, budget);
	return max_id+1;
end
$$

select addDept('new dpt',300);

drop function addDept;

create or replace function addDept(dpt_name department.dept_name%type, budget department.monthly_budget%type)
returns setof department
language plpgsql
as $$
declare 
	max_id integer;
begin
	select max(dept_id) into max_id
	from department;
	insert into department values ((max_id+1), dpt_name, budget);
	return query select * from department where dept_id =(max_id+1);
end
$$

create or replace function findAvgSal()
returns numeric(7,2)
language plpgsql
as $$
declare 
	avgSal numeric(7,2);
begin
	select round(avg(monthly_salary),2) into avgSal
	from employee;
	return avgSal;
end;
$$

select findAvgSal();

select *
from employee
where monthly_salary>findAvgSal();

create function square(x numeric)
returns integer
language plpgsql
as $$
begin
	return x*x;
end;
$$

select square(monthly_salary)
from employee;

create function applyTax(pre_tax employee.monthly_salary%type)
returns numeric(7,2)
language plpgsql
as $$ 
begin
	return (.75)*pre_tax;
end;
$$

select empl_name as "Employee", monthly_salary as "Pre tax income", applyTax(monthly_salary) as "Post tax income"
from employee;

create or replace function apply_tax_2(pre_tax employee.monthly_salary%type)
returns numeric(7,2)
language plpgsql
as $$
declare
	post_tax numeric;
begin
	if pre_tax<3000 then
		post_tax := (.78)*pre_tax;
	elseif pre_tax<4000 then 
		post_tax := (.75)*pre_tax;
	else 
		post_tax := (.7)*pre_tax;
	end if;
	return post_tax;	
end;
$$

select empl_name as "Employee", monthly_salary as "Pre tax income", applyTax(monthly_salary) as "Post tax income"
from employee;

select empl_name as "Employee", monthly_salary as "Pre tax income", apply_tax_2(monthly_salary) as "Post tax income"
from employee;

--update department
--set monthly_budget = monthly_budget + increase 
--where dept_id = current_id

/*
 * we can include a function for code we plan to reuse
 * that can be parameterized easily
 */
create or replace function increase_budget(increase department.monthly_budget%type, current_id department.dept_id%type)
returns void
language plpgsql
as $$
begin
	update department
	set monthly_budget = monthly_budget + increase
	where dept_id = current_id;
end;
$$

select * from department_spending;

/*
 * here we have a similar query to "department_spending" 
 *   but this result set includes the department id's
 */
select d.dept_id as "Department ID", d.dept_name as "Department Name", sum(e.monthly_salary) as "Budget Used", d.monthly_budget as "Budget"
from department d
join employee e
on d.dept_id = e.dept_id
group by d.dept_id, d.monthly_budget, d.dept_name;

select increase_budget(7000,4);

/*
 * refactored increase_budget function to return 
 *   the new budget value
 */
create or replace function increase_budget_2(increase department.monthly_budget%type, current_id department.dept_id%type)
returns numeric
language plpgsql
as $$
declare 
	current_budget numeric;
begin
	select monthly_budget into current_budget
	from department
	where dept_id = current_id;

	update department
	set monthly_budget = current_budget + increase
	where dept_id = current_id;

	return current_budget + increase;
end;
$$

/*
 * these statements now return to us the new budget value
 */
select increase_budget_2(11000, 1);
select increase_budget_2(3000, 3);

/*
 * we're going to create a function which attempts 
 * to give an employee a raise, but only if that 
 * employee's department's budget can allow for it 
 * without going over budget
 * 
 */ 

create or replace function assess_raise(input_id employee.empl_id%type, amount employee.monthly_salary%type)
returns numeric
language plpgsql
as $$ 
declare
	-- creating a placeholder to hold the department id of the employee 
	empl_dept_id employee.dept_id%type;
	dept_budget department.monthly_budget%type;
	budget_used department.monthly_budget%type;
	current_salary employee.monthly_salary%type;
begin
	-- get the department we're working with
	select dept_id into empl_dept_id
	from employee
	where empl_id = input_id;
	
	-- get the department budget using the employee id
	select monthly_budget into dept_budget
	from department
	where dept_id = empl_dept_id;
		
	-- get the value for budget used
	select sum(monthly_salary) into budget_used
	from employee
	where dept_id = empl_dept_id;

	-- getting the current salary of the employee 
	select monthly_salary into current_salary
	from employee
	where empl_id = input_id;

	if (budget_used+amount)>dept_budget then
		return current_salary;
	else
		update employee
		set monthly_salary = monthly_salary + amount
		where empl_id = input_id;
		return current_salary + amount;
	end if;	
end;
$$

select *
from employee
where empl_id= 13;

select assess_raise(13,200);



   
create or replace function fib1(n integer) 
   returns integer 
   language plpgsql as $$ 
declare
  num1 integer := 0; 
  num2 integer := 1;
  placeholder integer;
  counter integer :=0;
begin
 
	if n=0 then
		return 0;
	elseif n=1 then
		return 1;
	end if;
	
   
    loop 
      	exit when counter = n; 
      	counter := counter + 1 ; 
      
      	placeholder := num1;
		num1 := num1+num2;
		num2 := placeholder;
	
    end loop ; 
   
   return num1;
end ; 
$$ 

select fib1(3);


create or replace function fib2(n integer) 
   returns integer 
   language plpgsql as $$ 
declare
  num1 integer := 0; 
  num2 integer := 1;
  placeholder integer;
begin
 
	if n=0 then
		return 0;
	elseif n=1 then
		return 1;
	end if;
	
	for i in 0..(n-1) loop
		placeholder := num1;
		num1 := num1+num2;
		num2 := placeholder;
   	end loop;
   
   return num1;
end ; 
$$ 

insert into department values (1,'new dept',500);


create or replace function check_dept_id_uniqueness()
returns trigger
language plpgsql as 
$$
declare
	id_count integer;
begin
	select count(dept_id) into id_count
	from department
	where dept_id = new.dept_id;
	
	if id_count>0 then 
		new.dept_id := nextval('department_dept_id_seq'::regclass);
	end if;	
	
	return new;
end;
$$

create trigger assure_dept_id_uniquness
before insert 
on department
for each row
execute procedure check_dept_id_uniqueness();



