-- Lab 4 
-- Ruchika Shashidhara 
-- NU 002245068


-- Part A

CREATE DATABASE VolunteerAdministration;
USE VolunteerAdministration;

CREATE TABLE Person (
    PersonID int PRIMARY KEY NOT NULL,
    LastName varchar(50) NOT NULL CHECK (LastName NOT LIKE '%[^A-Za-z]%'),
    FirstName varchar(50) NOT NULL CHECK (FirstName NOT LIKE '%[^A-Za-z]%'),
    DateOfBirth date NOT NULL
);

CREATE TABLE Speciality (
    SpecialityID int PRIMARY KEY NOT NULL,
    Name varchar(50) NOT NULL,
    Description varchar(255)
);

CREATE TABLE Organization (
    OrganizationID int PRIMARY KEY NOT NULL,
    Name varchar(50) NOT NULL,
    MainPhone varchar(50) CHECK (MainPhone NOT LIKE '%[A-Za-z]%')
);

CREATE TABLE Volunteering (
    VolunteeringID int PRIMARY KEY NOT NULL,
    PersonID int NOT NULL,
    OrganizationID int NOT NULL,
    SpecialityID int NOT NULL,
    FOREIGN KEY (PersonID) REFERENCES Person(PersonID),
    FOREIGN KEY (OrganizationID) REFERENCES Organization(OrganizationID),
    FOREIGN KEY (SpecialityID) REFERENCES Speciality(SpecialityID)
);


-- Part B - 1

USE AdventureWorks2008R2;

WITH RankedCustomers AS (
SELECT
	YEAR(soh.OrderDate) Year,
	soh.CustomerID,
	SUM(soh.TotalDue) TotalPurchaseAmounts,
	DENSE_RANK() OVER (PARTITION BY YEAR(soh.OrderDate)
ORDER BY
	SUM(soh.TotalDue) DESC) TotalPurchaseRank
FROM
	Sales.SalesOrderHeader soh
GROUP BY
	soh.CustomerID,
	YEAR(soh.OrderDate) 
)
SELECT
	rc.Year,
	CAST(SUM(rc.TotalPurchaseAmounts) AS INT) [Total Sale],
	STRING_AGG(rc.CustomerID,
	', ') Top3Customers
FROM
	RankedCustomers rc
WHERE
	rc.TotalPurchaseRank <= 3
GROUP BY
	rc.Year

-- Part B - 2
	
USE AdventureWorks2008R2;
	
WITH 
RankedOrderQuantities AS (
SELECT
	soh.SalesOrderID,
		soh.SalesPersonID,
		SUM(sod.OrderQty) TotalSoldQuantity,
		ROW_NUMBER() OVER (
ORDER BY
	SUM(sod.OrderQty) DESC) SalesPersonRank
FROM
	Sales.SalesOrderHeader soh
INNER JOIN Sales.SalesOrderDetail sod ON
	soh.SalesOrderID = sod.SalesOrderID
WHERE
	soh.SalesPersonID IS NOT NULL
GROUP BY
	soh.SalesOrderID,
	soh.SalesPersonID
),
Top3SalesPersonTotalProductQuantities AS (
SELECT
	roq.SalesOrderID,
	roq.SalesPersonID,
	roq.TotalSoldQuantity
FROM
	RankedOrderQuantities roq
WHERE
	roq.SalesPersonID IN (
	SELECT
		DISTINCT(roq.SalesPersonID)
	FROM
		RankedOrderQuantities roq
	WHERE
		roq.SalesPersonRank <= 3
	)
),
RequiredLowestTotalProductQuantities AS (
SELECT
	l.SalesPersonID, 
	COUNT(l.SalesOrderID) TotalOrderCount, 
	MIN(TotalSoldQuantity) LowestQuantity
FROM
	Top3SalesPersonTotalProductQuantities l
GROUP BY
	l.SalesPersonID
),
RankedOrderValues AS (
SELECT
	l.SalesOrderID,
	l.SalesPersonID,
	l.TotalSoldQuantity,
	soh.TotalDue,
	ROW_NUMBER() OVER (PARTITION BY l.SalesPersonID
ORDER BY
	soh.TotalDue ) SalesOrderRank
FROM
	Top3SalesPersonTotalProductQuantities l
INNER JOIN Sales.SalesOrderHeader soh 
ON
	l.SalesOrderID = soh.SalesOrderID
 ),
RequiredLowestOrderValues AS (
SELECT
	rov.SalesPersonID,
	STRING_AGG(rov.TotalDue,
	', ') Lowest3Values
FROM
	RankedOrderValues rov
WHERE
	rov.SalesOrderRank <= 3
GROUP BY
	rov.SalesPersonID
)
SELECT
	q.SalesPersonID,
	q.TotalOrderCount,
	q.LowestQuantity,
	l3.Lowest3Values
FROM
	RequiredLowestTotalProductQuantities q
INNER JOIN RequiredLowestOrderValues l3
ON
	q.SalesPersonID = l3.SalesPersonID
;

-- Part C

USE AdventureWorks2008R2;

WITH Parts(AssemblyID,
ComponentID,
PerAssemblyQty,
EndDate,
ComponentLevel) AS
(
SELECT
	b.ProductAssemblyID,
	b.ComponentID,
	b.PerAssemblyQty,
	b.EndDate,
	0 AS ComponentLevel
FROM
	Production.BillOfMaterials AS b
WHERE
	b.ProductAssemblyID = 992
	AND b.EndDate IS NULL
UNION ALL
SELECT
	bom.ProductAssemblyID,
	bom.ComponentID,
	bom.PerAssemblyQty,
	bom.EndDate,
	ComponentLevel + 1
FROM
	Production.BillOfMaterials AS bom
INNER JOIN Parts AS p
ON
	bom.ProductAssemblyID = p.ComponentID
	AND bom.EndDate IS NULL
),
ComponentPriceRanked AS (
SELECT
	AssemblyID,
	ComponentID,
	Name,
	PerAssemblyQty,
	ComponentLevel,
	ListPrice,
	RANK() OVER (PARTITION BY ComponentLevel
ORDER BY
	ListPrice DESC) ComponentPriceRank
FROM
	Parts AS p
INNER JOIN Production.Product AS pr
ON
	p.ComponentID = pr.ProductID
WHERE
	ListPrice <> 0 )
SELECT
	cpr.AssemblyID,
	cpr.ComponentID,
	cpr.Name,
	cpr.PerAssemblyQty,
	cpr.ComponentLevel,
	cpr.ListPrice
FROM
	ComponentPriceRanked cpr
WHERE
	cpr.ComponentPriceRank = 1





