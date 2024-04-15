-- Ruchika Shashidhara NU002245068 
-- Lab 5

-- 5-1

USE AdventureWorks2008R2; 

WITH YearlySales as (
select datepart(yy, OrderDate) Year,
CONCAT(cast(SalesPersonID as varchar) + ' ',
 FirstName + ' ',
 LastName) as SalesPerson ,
 cast(sum(TotalDue) as int) as TotalSales
from Sales.SalesOrderHeader sh
join Person.Person p
on sh.SalesPersonID = p.BusinessEntityID
where year(OrderDate) in (2006, 2007) and SalesPersonID between 275 and 278
group by SalesPersonID, datepart(yy, OrderDate), FirstName, LastName
having sum(TotalDue) > 1500000
)
SELECT CAST(Year AS VARCHAR(10)) Year,
	CASE WHEN [275 Michael Blythe] IS NULL THEN '' 
		ELSE CAST([275 Michael Blythe] AS VARCHAR(10)) 
		END '275 Michael Blythe',
	CASE WHEN [276 Linda Mitchell] IS NULL THEN '' 
		ELSE CAST([276 Linda Mitchell] AS VARCHAR(10)) 
		END '276 Linda Mitchell',
	CASE WHEN [277 Jillian Carson] IS NULL THEN '' 
		ELSE CAST([277 Jillian Carson] AS VARCHAR(10)) 
		END '277 Jillian Carson',
	CASE WHEN [278 Garrett Vargas] IS NULL THEN '' 
		ELSE CAST([278 Garrett Vargas] AS VARCHAR(10)) 
		END '278 Garrett Vargas'
FROM (SELECT Year, SalesPerson, TotalSales FROM YearlySales) 
	VERTICAL
	PIVOT 
		(
			MAX(TotalSales) 
			FOR SalesPerson IN 
				(
					[275 Michael Blythe],
					[276 Linda Mitchell],
					[277 Jillian Carson],
					[278 Garrett Vargas]
				)
		) 
	HORIZONTAL
ORDER BY YEAR;


-- 5-2

USE Lab5_RS;  

CREATE FUNCTION dbo.GetCustomerFullName (@CustomerID INT)
RETURNS VARCHAR(1000)
AS
BEGIN
    DECLARE @FullName VARCHAR(1000);
    SELECT @FullName = RTRIM(p.LastName) + ' '+ RTRIM(p.FirstName)
    	FROM AdventureWorks2008R2.Sales.Customer c
	    INNER JOIN AdventureWorks2008R2.Person.Person p
	    ON c.PersonID = p.BusinessEntityID
    	WHERE c.CustomerID = @CustomerID ;
    RETURN @FullName;
END;

SELECT CustomerID, dbo.GetCustomerFullName(CustomerID)[Full Name]
FROM AdventureWorks2008R2.Sales.Customer;


-- 5-3

USE Lab5_RS;

CREATE FUNCTION dbo.GetUnpaidFinesCount(@StudentID INT, @RegisterDate DATE)
RETURNS INT
BEGIN
    DECLARE @Count INT = 0;
    SELECT @Count = 
        Count(f.StudentID)
        FROM Fine f
        WHERE 
        	f.StudentID = @StudentID 
        	AND (f.PaidDate IS NULL OR f.PaidDate > @RegisterDate)
    RETURN @Count;
END;

ALTER TABLE dbo.Enrollment 
	ADD CONSTRAINT CanEnrollIfNoUnpaidDues 
	CHECK (dbo.GetUnpaidFinesCount(StudentID, RegisterDate) = 0);


-- 5-4

USE Lab5_RS;

CREATE TRIGGER CalculateFees
ON dbo.OrderDetail
AFTER INSERT, UPDATE, DELETE 
AS
BEGIN
	SET NOCOUNT ON;
	DECLARE @OrderId INT;
	SELECT @OrderId = COALESCE(i.OrderID, d.OrderID)
		FROM Inserted i FULL JOIN Deleted d
		ON i.OrderID = d.OrderID;
	DECLARE @OrderValue MONEY;
	SELECT @OrderValue = SUM(od.Quantity * od.UnitPrice) 
		FROM dbo.OrderDetail od
		WHERE od.OrderID = @OrderId;
	UPDATE so SET so.OrderValue = @OrderValue
		FROM dbo.SalesOrder so
		WHERE so.OrderID = @OrderId;
	DECLARE @ShippingFeeFactor INT = 4;
	IF @OrderValue > 600
		BEGIN
			SELECT @ShippingFeeFactor = 2;
		END
	DECLARE @ShippingFee MONEY;
	SELECT @ShippingFee = SUM(od.Quantity) * @ShippingFeeFactor
		FROM dbo.OrderDetail od
		WHERE od.OrderID = @OrderId;
	UPDATE so SET so.ShippingFee = @ShippingFee
		FROM dbo.SalesOrder so
		WHERE so.OrderID = @OrderId;
END;

SELECT * FROM dbo.SalesOrder;


-- All Statements Used

-- 5-3

create table Course
(CourseID int primary key,
CourseName varchar(50),
InstructorID int,
AcademicYear int,
Semester smallint);

create table Student
(StudentID int primary key,
LastName varchar (50),
FirstName varchar (50),
Email varchar(30),
PhoneNumber varchar (20));

create table Enrollment
(CourseID int references Course(CourseID),
StudentID int references Student(StudentID),
RegisterDate date,
primary key (CourseID, StudentID));

create table Fine
(StudentID int references Student(StudentID),
IssueDate date,
Amount money,
PaidDate date
primary key (StudentID, IssueDate));

INSERT INTO Course (CourseID, CourseName, InstructorID, AcademicYear, Semester)
VALUES 
    (1, 'Introduction to Python Programming', 109, 2025, 1),
    (2, 'Machine Learning Fundamentals', 110, 2025, 2),
    (3, 'Software Engineering Principles', 111, 2026, 1),
    (4, 'Cybersecurity Essentials', 112, 2026, 2);

INSERT INTO Student (StudentID, LastName, FirstName, Email, PhoneNumber)
VALUES
    (1001, 'Taylor', 'Christopher', 'christopher.taylor@example.com', '901-234-5678'),
    (1002, 'Thomas', 'Amanda', 'amanda.thomas@example.com', '012-345-6789'),
    (1003, 'Hernandez', 'Maria', 'maria.hernandez@example.com', '123-456-7890'),
    (1004, 'Moore', 'James', 'james.moore@example.com', '234-567-8901');

INSERT INTO Fine (StudentID, IssueDate, Amount, PaidDate)
VALUES
    (1001, '2025-06-15', 30.00, NULL),
    (1002, '2025-07-20', 25.00, NULL),
    (1003, '2026-02-10', 20.00, '2026-03-01'),
    (1004, '2026-04-05', 15.00, NULL);

INSERT INTO Enrollment (CourseID, StudentID, RegisterDate)
VALUES (2, 1003, '2026-06-01');

INSERT INTO Enrollment (CourseID, StudentID, RegisterDate)
VALUES (4, 1004, '2026-07-01');

INSERT INTO Enrollment (CourseID, StudentID, RegisterDate)
VALUES (1, 1001, '2025-07-01');

-- 5-4

create table Customer
(CustomerID int primary key,
LastName varchar(50),
FirstName varchar(50),
Membership varchar(10));

create table SalesOrder
(OrderID int primary key,
CustomerID int references Customer(CustomerID),
OrderDate date,
ShippingFee money,
Tax as OrderValue * 0.08,
OrderValue money);

create table OrderDetail
(OrderID int references SalesOrder(OrderID),
ProductID int,
Quantity int,
UnitPrice money
primary key(OrderID, ProductID));

SELECT * FROM dbo.Customer;
SELECT * FROM dbo.SalesOrder;
SELECT * FROM dbo.OrderDetail;

INSERT INTO Customer (CustomerID, LastName, FirstName, Membership)
VALUES
    (1, 'Shelly', 'Cooper', 'C1'),
    (2, 'Leo', 'Hoff', 'C2'),
    (3, 'Howard', 'Wol', 'C3'),
    (4, 'Raj', 'Kooth', 'C4');
   
INSERT INTO SalesOrder (OrderID, CustomerID, OrderDate)
VALUES
	(1001, 1, '2023-03-01'),
	(1002, 2, '2023-03-01');

INSERT INTO OrderDetail (OrderID, ProductID, Quantity, UnitPrice)
VALUES
    (1002, 101, 5, 10);
INSERT INTO OrderDetail (OrderID, ProductID, Quantity, UnitPrice)
VALUES
    (1002, 102, 5, 10);
INSERT INTO OrderDetail (OrderID, ProductID, Quantity, UnitPrice)
VALUES
    (1002, 103, 1, 600);
INSERT INTO OrderDetail (OrderID, ProductID, Quantity, UnitPrice)
VALUES
    (1002, 104, 1, 100);
INSERT INTO OrderDetail (OrderID, ProductID, Quantity, UnitPrice)
VALUES
    (1002, 105, 1, 100);
   
UPDATE od
SET od.UnitPrice = 10
FROM OrderDetail od
WHERE od.ProductID = 105 AND od.OrderID = 1002

DELETE FROM OrderDetail
WHERE OrderID = 1002
  AND ProductID = 103;
 
INSERT INTO OrderDetail (OrderID, ProductID, Quantity, UnitPrice)
VALUES (1001, 101, 5, 10);
   
INSERT INTO OrderDetail (OrderID, ProductID, Quantity, UnitPrice)
VALUES (1001, 102, 1, 10);
   
INSERT INTO OrderDetail (OrderID, ProductID, Quantity, UnitPrice)
VALUES (1001, 103, 5, 600);

UPDATE od
SET od.UnitPrice = 10
FROM OrderDetail od
WHERE od.ProductID = 103 AND od.OrderID = 1001

INSERT INTO OrderDetail (OrderID, ProductID, Quantity, UnitPrice)
VALUES (1001, 104, 1, 600);

DELETE FROM OrderDetail
WHERE OrderID = 1001
AND ProductID = 104;














