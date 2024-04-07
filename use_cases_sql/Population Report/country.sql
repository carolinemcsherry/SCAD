SELECT
    country.name AS Country_Name,
    SUM(country.Population) AS Total_Country_Population,
    SUM(city.Population) AS Total_City_Population,
    ROUND((SUM(city.Population) / SUM(country.Population)) * 100, 2) AS City_Population_Percentage,
    SUM(country.Population) - SUM(city.Population) AS Total_Not_City_Population,
    ROUND(((SUM(country.Population) - SUM(city.Population)) / SUM(country.Population)) * 100, 2) AS None_City_Population_Percentage
FROM
    country
        JOIN
    city ON country.Code = city.CountryCode
GROUP BY
    country.name;

