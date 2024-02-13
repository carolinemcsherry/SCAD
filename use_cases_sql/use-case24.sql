# user story 24
# All the cities in a continent organised by largest population to smallest

select country.Continent, 
country.Name as 'County Name', 
city.Name as 'City Name', 
city.Population as 'Population descending'
from city
JOIN country ON country.Code = city.CountryCode # joining the table country to countrylanguage
where country.Continent = 'Asia'  #This will be user defind var
order by city.Population desc;