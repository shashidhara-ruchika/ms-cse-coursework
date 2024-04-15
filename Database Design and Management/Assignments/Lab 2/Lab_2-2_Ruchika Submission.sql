-- Ruchika Shashidhara NU002245068 
-- Lab 2-2 

USE AdventureWorks2008R2;


--2.1

SELECT
	ProductID [Product ID],
	Name,
	ListPrice [List Price],
	CAST(SellStartDate AS DATE) [Selling Start Date]
FROM
	Production.Product
WHERE
	ListPrice > 
	(
	SELECT
		MAX(ListPrice)
	FROM
		Production.Product) - 10
ORDER BY
	SellStartDate;


-- 2.2

SELECT
	CustomerID [Customer ID],
	MAX(OrderDate) [Most Recent Order Date],
	COUNT(*) [Total Number of Orders]
FROM
	Sales.SalesOrderHeader
GROUP BY
	CustomerID
ORDER BY
	[Total Number of Orders] DESC ;


-- 2.3

SELECT
	st.TerritoryID [Territory ID],
	st.Name [Territory Name],
	CAST(COUNT(soh.SalesOrderID) AS FLOAT) / NULLIF(COUNT(DISTINCT soh.CustomerID),
	0) [Orders to Customer Ratio]
FROM
	Sales.SalesOrderHeader soh
JOIN 
    Sales.SalesTerritory st ON
	soh.TerritoryID = st.TerritoryID
GROUP BY
	st.TerritoryID,
	st.Name
HAVING
	CAST(COUNT(soh.SalesOrderID) AS FLOAT) / NULLIF(COUNT(DISTINCT soh.CustomerID),
	0) >= 5
ORDER BY
	st.TerritoryID;


--2.4
  
SELECT
	c.CustomerID [Customer ID],
	ISNULL(p.FirstName,
	'') [First Name],
	ISNULL(p.LastName,
	'') [Last Name],
	ISNULL(ea.EmailAddress,
	'') [Email Address]
FROM
	Sales.Customer c
LEFT JOIN 
    Person.Person p ON
	c.PersonID = p.BusinessEntityID
LEFT JOIN 
    Person.EmailAddress ea ON
	p.BusinessEntityID = ea.BusinessEntityID
WHERE
	c.CustomerID BETWEEN 25000 AND 27000
ORDER BY
	c.CustomerID ;


-- 2.5

SELECT
	YEAR(OrderDate) [Year],
	SUM(OrderQty) [Total Product Quantity Sold for the Year]
FROM
	Sales.SalesOrderHeader soh
JOIN 
    Sales.SalesOrderDetail sod ON
	soh.SalesOrderID = sod.SalesOrderID
GROUP BY
	YEAR(OrderDate)
HAVING
	MAX(TotalDue) <= 150000
ORDER BY
	SUM(OrderQty) DESC;


-- 2.6

SELECT 
	TerritoryID [Territory ID],
	CAST(SUM(soh.TotalDue) AS INT) [Total Sales of Orders on all New Year days]
FROM
	Sales.SalesOrderHeader soh
INNER JOIN 
    Sales.SalesOrderDetail sod ON
	soh.SalesOrderID = sod.SalesOrderID
INNER JOIN 
    Production.Product p ON
	sod.ProductID = p.ProductID
WHERE
	MONTH(soh.OrderDate) = 1
	AND DAY(soh.OrderDate) = 1
	AND EXISTS (
	SELECT
		1
	FROM
		Sales.SalesOrderDetail sodx
	JOIN Production.Product px on
		sodx.ProductID = px.ProductID
	WHERE
		sodx.SalesOrderID = soh.SalesOrderID
		AND px.Color = 'Black'
	)
GROUP BY
	TerritoryID
HAVING
	COUNT(DISTINCT sod.ProductID) > 40
ORDER BY
	TerritoryID;
