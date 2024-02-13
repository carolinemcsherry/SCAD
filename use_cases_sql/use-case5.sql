# user story 5 number of people who speak Chinese.
# English.
# Hindi.
# Spanish.
# Arabic.
#  from greatest number to smallest, including the percentage of the world population


SELECT countrylanguage.Language, 
SUM(ROUND(countrylanguage.Percentage / 100 * country.Population)) AS speakers_world_wide,
CONCAT(round((SUM(ROUND(countrylanguage.Percentage / 100 * country.Population)) / (SELECT SUM(Population) FROM country)) * 100,2), '%') AS '% of speakers world wide'
FROM countrylanguage
JOIN country ON country.Code = countrylanguage.CountryCode # joining the table country to countrylanguage
WHERE countrylanguage.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') #  specafiling the 5 asked for languages 
GROUP BY countrylanguage.Language
ORDER BY speakers_world_wide DESC; # this is ordering the from greatest number to smallest
