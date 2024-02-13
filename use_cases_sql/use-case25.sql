# User Story  25 
# All the cities in the world organised by largest population to smallest.

select country.Continent, 
country.Name as 'County Name', 
city.Name as 'City Name', 
city.District, 
city.Population as 'Population descending'
from city
JOIN country ON country.Code = city.CountryCode # joining the table country to countrylanguage
order by city.Population desc;