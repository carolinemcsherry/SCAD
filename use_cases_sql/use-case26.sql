# User story 26
# The top N populated countries in the world where N is provided by the user.

select * 
from country
order by country.Population desc
limit 5; #This will be user defind var;