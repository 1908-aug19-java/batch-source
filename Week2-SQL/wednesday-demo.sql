-- finding the department which our lowest paid employee belongs
select d.*
from department d
join employee e
on e.dept_id = d.dept_id
where e.monthly_salary = 
	(select min(monthly_salary)
	from employee);

-- sum of employee salaries based on department
select sum(e.monthly_salary) as "Total Spending", d.dept_name as "Department"
from department d
join employee e
on e.dept_id = d.dept_id
group by d.dept_name;


-- compare department spending to their budget 
select d.dept_name as "Department", sum(e.monthly_salary) as "Total Spending", d.monthly_budget as "Budget"
from department d
join employee e
on e.dept_id = d.dept_id
group by d.dept_name, d.monthly_budget;

/*
 * Creating views
 * - saved queries
 * - information in a view is not actually stored 
 *    in the database, rather the criteria to access 
 *    the information is 
 * 
 * create view [view name] as
 * [query]
 * 
 */
create view department_spending as 
select d.dept_name as "Department", sum(e.monthly_salary) as "Total Spending", d.monthly_budget as "Budget"
from department d
join employee e
on e.dept_id = d.dept_id
group by d.dept_name, d.monthly_budget;

select * from department_spending;


/*
 * Materialized View 
 * - a materialized view, unlike a normal view, 
 *   actually stores the results in memory
 * - the results are not going to automatically 
 *   update when the information the materialized 
 *   view is changed 
 * - the materialized view must be refreshed to 
 *   see those changes 
 */

create materialized view department_spending_2 as 
select d.dept_name as "Department", sum(e.monthly_salary) as "Total Spending", d.monthly_budget as "Budget"
from department d
join employee e
on e.dept_id = d.dept_id
group by d.dept_name, d.monthly_budget;

select * from department_spending;
select * from department_spending_2;

update employee
set monthly_salary = monthly_salary - 200
where empl_id = 14;

refresh materialized view department_spending_2;

