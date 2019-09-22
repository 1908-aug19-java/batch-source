/*2.1 SELECT      */
--Select all records from the Employee table.
select * from "Employee";
--Select all records from the Employee table where last name is King.
select * from "Employee"
where "LastName"='King';
--Select all albums in Album table and sort result set in descending order by title.
select * from "Album"
ORDER by "Title" desc;
--Select first name from Customer and sort result set in ascending order by city.
select "FirstName" from "Customer"
order by "FirstName" asc
--Select all invoices with a billing address like “T%”.
select * from "Invoice"
where "BillingAddress" like 'T%';
--Select the name of the longest track.
select "Name" from "Track"
where  "Milliseconds" =
       (select max("Milliseconds")
               from "Track") ;

--Find the average invoice total.
select  round(avg("Total"),2) as "Invoice Total"
 from "Invoice"; 

--Find the total number of employees in each position.
select count("Title") as "Number Of Employee", "Title" as "Employees Position"
from "Employee"
group by "Title"
order by count("Title") desc;

---------------------------------------2.2 INSERT INTO
------Insert two new records into Genre table
Insert into  "Genre"("GenreId","Name")
values(26,'BlueGrass'),(27,'Gnawa');
------Insert two new records into Employee table
insert into "Employee" ("EmployeeId", "LastName", "FirstName","Title")
values(55,'SqlMan','Mike','Database Admin'),
      (45,'JavaMan','John','Software Dev');
------Insert two new records into Customer table 
insert into "Customer"("CustomerId", "LastName", "FirstName","Company","Email")
values(60,'chef','mike','Revature','chefmike@gmail.com'),
      (61,'Tyson', 'tom','Microsoft','tyson@yahoo.com');
     
 -------------------------------------------2.3 UPDATE
-------Update Aaron Mitchell in Customer table to Robert Walter
update "Customer"
set ("FirstName","LastName")=('Robert', 'Walter')
where ("FirstName","LastName")=('Aaron','Mitchell')
-------Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
update "Artist"
set "Name"= 'CCR'
where "Name"='Creedence Clearwater Revival';

--------------------------------------3.0 Joins
--3.1 INNER
--------Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
select c."FirstName",c."LastName",i."InvoiceId" from "Customer" c
        inner join "Invoice" i
        on c."CustomerId" = i."CustomerId"
        order by c."FirstName";
--3.2 OUTER
--------Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
select c."FirstName",c."LastName",i."InvoiceId",i."Total" from "Customer" c
        full outer join "Invoice" i
        on c."CustomerId" = i."CustomerId"
      
--3.3 RIGHT
---------Create a right join that joins album and artist specifying artist name and title.
select ar."Name",al."Title" from "Album" al
         right join "Artist" ar
         on al."ArtistId"=ar."ArtistId";
         
--3.4 CROSS
----------Create a cross join that joins album and artist and sorts by artist name in ascending order.
-- the number of rows in the first table multiplied by the number of rows in the second table
select * from "Album"
cross join "Artist" ar
order by ar."Name" asc;
--3.5 SELF
-----------Perform a self-join on the employee table, joining on the reportsto column.
select e."FirstName" as "Employee First Name",e."LastName" as "Employee Last Name", s."FirstName"as "Supervisor First Name",s."LastName" as "Supervisor last Name"
from "Employee" e
join "Employee" s
on  s."EmployeeId"=e."ReportsTo";
-----------------4.0 User Defined Functions
------Create a function that returns the average total of all invoices.
create or replace function avgInvoices()

returns numeric(7,2)
 language plpgsql
as $$
declare
      avg_invoice numeric(7,2);
begin
	select round(avg("Total"),2) into avg_invoice
	from "Invoice";	
 return avg_invoice;
end; $$
-- call function
select avgInvoices();

------Create a function that returns all employees who are born after 1968.
create or replace function emplBornAfter()
returns setof "Employee"."FirstName"%type 
language plpgsql
as $$
 begin 
	return query  select "FirstName" ||' '|| "LastName" from "Employee" 
	 where "BirthDate" >'1968-01-01 00:00:00'
	 order by "BirthDate" asc;
 end; $$ 

 
 select emplBornAfter();
----------------

------Create a function that returns the manager of an employee, given the id of the employee.

create or replace function EmplManager(empl_id numeric)
returns varchar
language plpgsql
as $$
declare
    Manager_id integer;
    Manager_First_Name varchar;
begin
   select e."ReportsTo" into Manager_id 
   from "Employee" e
   where e."EmployeeId" =empl_id ;
   
   select m."FirstName"into Manager_First_Name
   from "Employee" m
   where m."EmployeeId" = Manager_id;
   return  Manager_First_Name;	
end;$$

select EmplManager(3);
------Create a function that returns the price of a particular playlist, given the id for that playlist.



create or replace function pricePlayList(P_Id integer) 
returns numeric(7,2)
language plpgsql
as $$
declare 
    Price_play numeric(7,2);
    play_track_id integer;
begin
	Price_play := ( select sum(t."UnitPrice") from "Track" t 
	                join(
	                     select * from "PlaylistTrack" pt
	                     where pt."PlaylistId" = P_Id 
	                     ) as play_track_id 
                     on t."TrackId" = play_track_id."TrackId"
                   );
	return Price_play;
end;$$

select pricePlayList(3);