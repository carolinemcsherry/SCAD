SELECT country.Continent, sum(country.Population) as Total_Continent_Population, 
SUM(city.Population) as Total_City_Population,
CONCAT(round(SUM(city.Population) / SUM(country.Population) * 100, 2),'%') as City_Population_Percentage,
SUM(country.Population)-SUM(city.Population) as Total_Not_City_Population,
CONCAT(100 - round(SUM(city.Population) / SUM(country.Population) * 100, 2) ,'%') as None_City_Population_Percentage
from country
JOIN city ON country.Code = city.CountryCode
GROUP BY country.Continent;