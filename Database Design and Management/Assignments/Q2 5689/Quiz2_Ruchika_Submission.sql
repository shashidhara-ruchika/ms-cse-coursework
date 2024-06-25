use AdventureWorks2008R2;

WITH QuarterlySales AS (
SELECT
	YEAR(so.OrderDate) AS OrderYear,
	DATEPART(QUARTER, so.OrderDate) AS OrderQuarter,
	p.Color,
	SUM(sod.UnitPrice * sod.OrderQty) AS TotalSales
FROM
	Sales.SalesOrderHeader so
INNER JOIN 
        Sales.SalesOrderDetail sod ON
	so.SalesOrderID = sod.SalesOrderID
INNER JOIN 
        Production.Product p ON
	sod.ProductID = p.ProductID
WHERE
	p.Color IS NOT NULL
GROUP BY
	YEAR(so.OrderDate),
	DATEPART(QUARTER, so.OrderDate),
	p.Color
),
TopQuarters AS (
SELECT
	OrderYear,
	OrderQuarter,
	Color,
	TotalSales,
	ROW_NUMBER() OVER(PARTITION BY Color
ORDER BY
	TotalSales DESC) AS QuarterRank
FROM
	QuarterlySales
),
QualifiedQuarters AS (
SELECT
	OrderYear,
	OrderQuarter,
	Color,
	TotalSales
FROM
	TopQuarters
WHERE
	QuarterRank <= 2
),
QualifiedOrders AS (
SELECT
	YEAR(so.OrderDate) AS OrderYear,
	DATEPART(QUARTER, so.OrderDate) AS OrderQuarter,
	p.Color,
	COUNT(DISTINCT so.SalesOrderID) AS OrderCount,
	SUM(so.TotalDue) AS TotalOrderValue
FROM
	Sales.SalesOrderHeader so
INNER JOIN 
        Sales.SalesOrderDetail sod ON
	so.SalesOrderID = sod.SalesOrderID
INNER JOIN 
        Production.Product p ON
	sod.ProductID = p.ProductID
WHERE
	p.Color IS NOT NULL
GROUP BY
	YEAR(so.OrderDate),
	DATEPART(QUARTER, so.OrderDate),
	p.Color
HAVING
	COUNT(DISTINCT so.SalesOrderID) > 12
		AND SUM(so.TotalDue) > 100000
),
RequiredValues AS (
SELECT
	qq.OrderYear,
	qq.OrderQuarter,
	qq.Color,
	qq.TotalSales,
	(MAX(qo.TotalOrderValue) / (qq.TotalSales * 100) ) AS PercentageOfTotalSales
FROM
	QualifiedQuarters qq
INNER JOIN 
    QualifiedOrders qo ON
	qq.OrderYear = qo.OrderYear
	AND qq.OrderQuarter = qo.OrderQuarter
	AND qq.Color = qo.Color
GROUP BY
	qq.OrderYear,
	qq.OrderQuarter,
	qq.Color,
	qq.TotalSales),
Report AS (
SELECT
	rv.Color,
	CAST (rv.OrderYear AS VARCHAR) + ' '
+ 'Quarter ' + CAST (rv.OrderQuarter AS VARCHAR) + ' ' 
+ CAST(qo.OrderCount AS VARCHAR) + ' Large Orders' + ' ' 
+ CAST ((rv.PercentageOfTotalSales) AS VARCHAR) + '%' AS Quarters
FROM
	RequiredValues rv
INNER JOIN QualifiedOrders qo ON
	rv.Color = qo.Color
	and rv.OrderYear = qo.OrderYear
	and rv.OrderQuarter = qo.OrderQuarter
)
SELECT
	Color,
	STRING_AGG(
cast(Quarters as varchar(1000))
,
	', ') as Top2Quarters
FROM
	Report
GROUP BY
	Color
;
   

use LabRS;

create table Advisor
(advisorid int primary key,
adviorlastname varchar(50) not null,
advisorfirstname varchar(50) not null);

create table venue
(venueid int primary key,
capacity int not null);

create table seminar
(seminarid int primary key,
seminardate date not null,
numberofadvisor tinyint not null,
venueid int not null references venue(venueid),
status varchar(10) not null, -- For simplicity, either Active or Complete
description varchar(100));

create table advisorseminar
(advisorid int references advisor(advisorid),
seminarid int references seminar(seminarid)
primary key (advisorid, seminarid));

create table client
(clientid int primary key,
clientlastname varchar(50) not null,
clientfirstname varchar(50) not null);

create table enrollment
(seminarid int not null references seminar(seminarid),
clientid int not null references client(clientid),
registrationdate date not null,
status varchar(10) not null -- For simplicity, either active or complete
primary key (seminarid, clientid));

create table audittrail
(audittrailid int identity primary key,
seminarid int not null,
timing datetime default getdate());

CREATE FUNCTION dbo.SeminarMonthlyAdvisorSeminarCount (@AdvisorID INT,
@SeminarID INT)
RETURNS INT
AS
BEGIN
	DECLARE @MonthlySeminarCount INT;

SELECT
	@MonthlySeminarCount = COUNT(*)
FROM
	advisorseminar AS a
JOIN seminar AS s ON
	a.seminarid = s.seminarid
WHERE
	a.advisorid = @AdvisorID
	AND YEAR(s.seminardate) = YEAR(GETDATE())
	AND MONTH(s.seminardate) = MONTH(GETDATE());

RETURN @MonthlySeminarCount;
END;


CREATE FUNCTION dbo.SeminarYealyAdvisorSeminarCount (@AdvisorID INT,
@SeminarID INT)
RETURNS INT
AS
BEGIN
	DECLARE @YearlySeminarCount INT;

SELECT
	@YearlySeminarCount = COUNT(*)
FROM
	advisorseminar AS a
JOIN seminar AS s ON
	a.seminarid = s.seminarid
WHERE
	a.advisorid = @AdvisorID
	AND YEAR(s.seminardate) = YEAR(GETDATE());

RETURN @YearlySeminarCount;
END;


ALTER TABLE advisorseminar
ADD CONSTRAINT AdvisorSeminarLimits
CHECK (
    (dbo.SeminarMonthlyAdvisorSeminarCount(advisorid,
seminarid) <= 12)
AND
    (dbo.SeminarYealyAdvisorSeminarCount(advisorid,
seminarid) <= 100)
);

   