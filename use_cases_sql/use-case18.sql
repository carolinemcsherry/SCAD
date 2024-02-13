select
A. Name as cityname,
B. Name as countryname,
A. Population
from city A
left join country B on A. CountryCode = B. Code;