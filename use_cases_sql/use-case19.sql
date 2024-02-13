# User story 19
# The top N populated cities in a continent where N is provided by the user.

select country.Name, city.Name, city.District, city.Population 
from city
JOIN country ON city.CountryCode = country.Code  
where city.CountryCode in 
(select code
from country
where country.Continent = 'North America') #This will be user defind var
order by city.Population desc
limit 5; #This will be user defind var