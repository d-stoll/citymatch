CREATE TABLE cities
(
	city TEXT,
	city_ascii TEXT,
	lat NUMERIC,
	lng NUMERIC,
	country TEXT,
	iso2 TEXT,
	iso3 TEXT,
	admin_name TEXT,
	capital TEXT,
	population NUMERIC,
	id INT
);

CREATE UNIQUE index cities_id_uindex
	on cities (id);

ALTER TABLE cities
	add constraint cities_pk
		primary key (id);

CREATE TABLE cities_tmp AS TABLE cities LIMIT 0;

ALTER TABLE cities_tmp ALTER population TYPE text;

COPY cities_tmp(city, city_ascii, lat, lng, country, iso2, iso3, admin_name, capital, population, id)
FROM '/init-data/worldcities.csv' DELIMITER ',' CSV HEADER;

INSERT INTO cities
SELECT city, city_ascii, lat, lng, country, iso2, iso3, admin_name, capital, NULLIF(population, '')::NUMERIC, id
FROM   cities_tmp;