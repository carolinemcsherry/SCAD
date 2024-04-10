SELECT country.Region,
                    SUM(country.Population) AS Total_Region_Population
                    FROM country where country.Region like  + input +  GROUP BY country.Region;
--retrieve data on population and continent