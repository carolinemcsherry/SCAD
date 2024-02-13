# User Story  23
# All the cities in a region organised by largest population to smallest.

select 
city.Name as 'City Name', 
city.District, 
city.Population as 'Population descending'
from city
JOIN country ON country.Code = city.CountryCode # joining the table country to countrylanguage
where country.Region = 'Caribbean' #This will be user defind var
order by city.Population desc;