SELECT A.Name AS cityname,
       A.Population AS city_population,
       B.Name AS countryname,
       B.Population AS country_population
FROM city A
LEFT JOIN country B ON A.CountryCode = B.Code;

-- retrieves city name, city  population,  country name and  country population