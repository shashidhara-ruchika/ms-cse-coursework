USE AdventureWorks2008R2;  

--- 2
WITH QuarterlySales AS (
SELECT
	pr.ProductID,
	YEAR(sord.OrderDate) AS SalesYear,
	DATEPART(QUARTER, sord.OrderDate) AS SalesQuarter,
	SUM(sod.UnitPrice * sod.OrderQty) AS TotalSalesDollar
FROM
	Sales.SalesOrderDetail sod
INNER JOIN
        Sales.SalesOrderHeader sord ON
	sod.SalesOrderID = sord.SalesOrderID
INNER JOIN
        Production.Product pr ON
	sod.ProductID = pr.ProductID
GROUP BY
	pr.ProductID,
	DATEPART(QUARTER, sord.OrderDate),
	YEAR(sord.OrderDate)
),
CountOfProductSales AS (
SELECT
	ProductID,
	COUNT(DISTINCT CONCAT(SalesYear, ':', SalesQuarter)) AS QuartersSold,
	SUM(TotalSalesDollar) AS TotalSales
FROM
	QuarterlySales
GROUP BY
	ProductID
HAVING
	COUNT(DISTINCT CONCAT(SalesYear, ':', SalesQuarter)) = (
	SELECT
		COUNT(DISTINCT CONCAT(YEAR(OrderDate), ':', DATEPART(QUARTER, OrderDate)))
	FROM
		Sales.SalesOrderHeader)
),
UniqueCustomerCounts AS (
SELECT
	sod.ProductID,
	COUNT(DISTINCT sord.CustomerID) AS UniqueCustomers
FROM
	Sales.SalesOrderDetail sod
JOIN
    Sales.SalesOrderHeader sord ON
	sod.SalesOrderID = sord.SalesOrderID
GROUP BY
	sod.ProductID
HAVING
	COUNT(DISTINCT sord.CustomerID) > 2000
)
SELECT
	cps.ProductID [Product ID],
	cps.QuartersSold [Number of Quarters Sold],
	CAST(cps.TotalSales AS INT) [Total Sales Dollar Amount]
FROM
	CountOfProductSales cps
JOIN
     UniqueCustomerCounts ON
	cps.ProductID = UniqueCustomerCounts.ProductID
ORDER BY
	cps.ProductID;

---- 3
WITH RankedSales AS (
    SELECT 
        sp.BusinessEntityID AS SalespersonID,
        YEAR(so.OrderDate) AS Year,
        COUNT(DISTINCT a.StateProvinceID) AS UniqueStatesProvinces,
        SUM(so.TotalDue) AS TotalSalesAmount,
        RANK() OVER (PARTITION BY YEAR(so.OrderDate) ORDER BY COUNT(DISTINCT a.StateProvinceID) DESC) AS StateProvinceRank,
        RANK() OVER (PARTITION BY YEAR(so.OrderDate) ORDER BY SUM(so.TotalDue) DESC) AS SalesAmountRank
    FROM 
        Sales.SalesOrderHeader so
    JOIN 
        Person.Address a ON so.ShipToAddressID = a.AddressID
    JOIN 
        Sales.SalesPerson sp ON so.SalesPersonID = sp.BusinessEntityID
    WHERE 
        so.SalesPersonID IS NOT NULL
    GROUP BY 
        sp.BusinessEntityID, YEAR(so.OrderDate)
    HAVING 
        MIN(so.TotalDue) >= 10
)
SELECT 
    SalespersonID,
    Year,
    UniqueStatesProvinces,
    CAST(TotalSalesAmount AS INT) AS TotalSalesAmount
FROM 
    RankedSales
WHERE 
    StateProvinceRank <= 5 AND SalesAmountRank <= 5
ORDER BY 
    Year;

