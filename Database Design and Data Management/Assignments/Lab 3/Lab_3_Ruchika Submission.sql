-- Ruchika Shashidhara NU002245068 
-- Lab 3

USE AdventureWorks2008R2;

--Lab 3-1

/* Modify the following query to add a column that identifies the
 frequency of repeat customers and contains the following values
 based on the number of orders:
 'No Order' for count = 0
 'One Time' for count = 1
 'Regular' for count range of 2-5
 'Often' for count range of 6-10
 'Loyal' for count greater than 10
 Give the new column an alias to make the report more readable. 
*/

SELECT c.CustomerID, c.TerritoryID, FirstName, LastName,
COUNT(o.SalesOrderid) [Total Orders],
	CASE
		WHEN COUNT(o.SalesOrderid) = 0 THEN 'No Order'
		WHEN COUNT(o.SalesOrderid) = 1 THEN 'One Time'
		WHEN COUNT(o.SalesOrderid) BETWEEN 2 AND 5 THEN 'Regular'
		WHEN COUNT(o.SalesOrderid) BETWEEN 6 AND 10 THEN 'Often'
		ELSE 'Loyal'
	END [Customer Frequency Label]
FROM Sales.Customer c
JOIN Sales.SalesOrderHeader o
 ON c.CustomerID = o.CustomerID
JOIN Person.Person p
 ON p.BusinessEntityID = c.PersonID
WHERE c.CustomerID > 25000 
GROUP BY c.TerritoryID, c.CustomerID, FirstName, LastName;

-- Lab 3-2

/* Modify the following query to add a rank without gaps in the
 ranking based on total orders in the descending order. Also
 partition by territory.*/

SELECT o.TerritoryID, s.Name, year(o.OrderDate) Year,
 COUNT(o.SalesOrderid) [Total Orders],
	 DENSE_RANK() OVER
	 (PARTITION BY o.TerritoryID ORDER BY COUNT(o.SalesOrderid) DESC) 
	 AS [Total Orders by Territory Rank]
FROM Sales.SalesTerritory s 
JOIN Sales.SalesOrderHeader o
 ON s.TerritoryID = o.TerritoryID
GROUP BY o.TerritoryID, s.Name, year(o.OrderDate)
ORDER BY o.TerritoryID;

-- Lab 3-3

/* Write a query to retrieve the most valuable customer of each year.
 The most valuable customer of a year is the customer who has 
 made the most purchase for the year. Use the yearly sum of the 
 TotalDue column in SalesOrderHeader as a customer's total purchase 
 for a year. If there is a tie for the most valuable customer, 
 your solution should retrieve it.
 Include the customer's id, total purchase, and total order count 
 for the year. Display the total purchase as an integer using CAST.
 Sort the returned data by the year. */

WITH [Yearly Sales] AS (
	SELECT 
		CustomerID [Customer ID],
	    YEAR(OrderDate) [Order Year],
	    SUM(TotalDue) [Total Purchase],
	    COUNT(*) [Total Order Count],
	    RANK() OVER
	    	(PARTITION BY YEAR(OrderDate) ORDER BY SUM(TotalDue) DESC) 
	    	[Valuable Customer Rank]
	FROM 
	    Sales.SalesOrderHeader
	GROUP BY 
	    YEAR(OrderDate),
	    CustomerID
) 
SELECT 
	[Customer ID], 
	[Order Year], 
	CAST ([Total Purchase] AS INT) [Total Purchase], 
	[Total Order Count]
FROM [Yearly Sales]
WHERE [Valuable Customer Rank] = 1
ORDER BY [Order Year]


-- Lab 3-4

/* Provide a unique list of customer id’s which have ordered both 
 the red and yellow products after May 1, 2008. Sort the list by customer id. */


WITH [Product Orders] AS (
	SELECT soh.CustomerID [Customer ID], p.Color
    FROM Sales.SalesOrderHeader soh
    JOIN Sales.SalesOrderDetail sod ON soh.SalesOrderID = sod.SalesOrderID
    JOIN Production.Product p ON sod.ProductID = p.ProductID
    WHERE soh.OrderDate > '2008-05-01'
),
[Red Product Orders] AS (
	SELECT DISTINCT [Customer ID]
	FROM [Product Orders] po
	WHERE po.Color = 'Red'
),
[Yellow Product Orders] AS (
	SELECT DISTINCT [Customer ID]
	FROM [Product Orders] po
	WHERE po.Color = 'Yellow'
)
SELECT DISTINCT rpo.[Customer ID]
FROM [Red Product Orders] rpo
JOIN [Yellow Product Orders] ypo
ON rpo.[Customer ID] = ypo.[Customer ID]
ORDER BY [Customer ID];

-- Lab 3-5

/* 
Use the content of AdventureWorks2008R2, 
write a query that returns
the Territory which had the smallest difference between the total sold value 
of the most sold product color and the total sold value of the least sold 
product color. 
In the same query, also return the territory which had the 
largest difference between the total sold value of the most sold product color 
and the total sold value of the least sold product color. 
If there is a tie, the tie must be returned. 

Exclude the sold products which didn't have a color specified for this query.
The most sold product color had the highest total sold value. 
The least sold product color had the lowest total sold value. 
Use UnitPrice * OrderQty to calculate the total sold value. 
UnitPrice and OrderQty are in Sales.SalesOrderDetail.
Include only the orders which had a total due greater than $65000 for this query. 
Include the TerritoryID, highest total, lowest total, and difference in the returned data. 
Format the numbers as an integer. 
Sort the returned data by TerritoryID in asc. 
*/

WITH [Product Sales] AS (
    SELECT 
        soh.TerritoryID [Territory ID], 
        p.Color [Color], 
        SUM(sod.UnitPrice * sod.OrderQty) [Total Sold Value]
    FROM Sales.SalesOrderHeader soh
    JOIN Sales.SalesOrderDetail sod ON soh.SalesOrderID = sod.SalesOrderID
    JOIN Production.Product p ON sod.ProductID = p.ProductID
    WHERE soh.TotalDue > 65000 AND p.Color IS NOT NULL
    GROUP BY soh.TerritoryID, p.Color
),
[Territory Summary] AS (
    SELECT
        [Territory ID],
        [Color],
        [Total Sold Value],
        RANK() OVER
        	(PARTITION BY [Territory ID] ORDER BY [Total Sold Value] DESC) 
        	[Most Sold Product Rank],
        RANK() OVER(
        	PARTITION BY [Territory ID] ORDER BY [Total Sold Value] ASC) 
        	[Least Sold Product Rank]
    FROM [Product Sales]
),
[Territory Difference] AS (
    SELECT
        [Territory ID],
        MAX(CASE WHEN 
        	[Most Sold Product Rank] = 1 THEN [Total Sold Value] END) 
        	[Highest Total Sold Value],
        MAX(CASE WHEN 
        	[Least Sold Product Rank] = 1 THEN [Total Sold Value] END) 
        	[Lowest Total Sold Value]
    FROM [Territory Summary]
    GROUP BY [Territory ID]
),
[Total Sold Value Difference Summary] AS (
    SELECT
        [Territory ID],
        [Highest Total Sold Value],
        [Lowest Total Sold Value],
        [Highest Total Sold Value] - [Lowest Total Sold Value] AS [Total Sold Value Difference]
    FROM [Territory Difference]
)
SELECT 
	[Territory ID],
    CAST([Highest Total Sold Value] AS INT) [Highest Total],
    CAST([Lowest Total Sold Value] AS INT) [Lowest Total], 
    CAST([Highest Total Sold Value] - [Lowest Total Sold Value] AS INT) 
    	AS [Total Sold Value Difference]
FROM [Total Sold Value Difference Summary]
WHERE 
	[Total Sold Value Difference] 
		= (SELECT MAX([Total Sold Value Difference]) 
			FROM [Total Sold Value Difference Summary])
   OR 
  	[Total Sold Value Difference] 
   		= (SELECT MIN([Total Sold Value Difference]) 
   			FROM [Total Sold Value Difference Summary])
ORDER BY [Territory ID];



WITH [Product Sales] AS (
    SELECT 
        soh.TerritoryID [Territory ID], 
        p.Color [Color], 
        SUM(sod.UnitPrice * sod.OrderQty) [Total Sold Value]
    FROM Sales.SalesOrderHeader soh
    JOIN Sales.SalesOrderDetail sod ON soh.SalesOrderID = sod.SalesOrderID
    JOIN Production.Product p ON sod.ProductID = p.ProductID
    WHERE soh.TotalDue > 65000 AND p.Color IS NOT NULL
    GROUP BY soh.TerritoryID, p.Color
),
[Territory Summary] AS (
    SELECT
        [Territory ID],
        [Color],
        [Total Sold Value],
        RANK() OVER
        	(PARTITION BY [Territory ID] ORDER BY [Total Sold Value] DESC) 
        	[Most Sold Product Rank],
        RANK() OVER(
        	PARTITION BY [Territory ID] ORDER BY [Total Sold Value] ASC) 
        	[Least Sold Product Rank]
    FROM [Product Sales]
),
[Territory Difference] AS (
    SELECT
        [Territory ID],
        MAX(CASE WHEN 
        	[Most Sold Product Rank] = 1 THEN [Total Sold Value] END) 
        	[Highest Total Sold Value],
        MAX(CASE WHEN 
        	[Least Sold Product Rank] = 1 THEN [Total Sold Value] END) 
        	[Lowest Total Sold Value]
    FROM [Territory Summary]
    GROUP BY [Territory ID]
),
[Total Sold Value Difference Summary] AS (
    SELECT
        [Territory ID],
        [Highest Total Sold Value],
        [Lowest Total Sold Value],
        [Highest Total Sold Value] - [Lowest Total Sold Value] AS [Total Sold Value Difference]
    FROM [Territory Difference]
)
SELECT 
	[Territory ID],
    CAST([Highest Total Sold Value] AS INT) [Highest Total],
    CAST([Lowest Total Sold Value] AS INT) [Lowest Total], 
    CAST([Highest Total Sold Value] - [Lowest Total Sold Value] AS INT) 
    	AS [Total Sold Value Difference]
FROM [Total Sold Value Difference Summary]
WHERE 
	[Total Sold Value Difference] 
		= (SELECT MAX([Total Sold Value Difference]) 
			FROM [Total Sold Value Difference Summary])
   OR 
  	[Total Sold Value Difference] 
   		= (SELECT MIN([Total Sold Value Difference]) 
   			FROM [Total Sold Value Difference Summary])
ORDER BY [Territory ID];

