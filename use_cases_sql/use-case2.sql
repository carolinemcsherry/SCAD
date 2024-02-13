#user case 2
#
select city.Name as Capital ,
country.name as Country, city.Population  from city
JOIN country ON country.Code = city.CountryCode
where id in (select country.Capital from country);

