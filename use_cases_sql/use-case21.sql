# User Story 21
# All the cities in a district organised by largest population to smallest.

select * from city 
where city.District = 'Zytomyr' #This will be user defind var
order by city.Population desc;