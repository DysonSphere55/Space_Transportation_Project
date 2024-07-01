CREATE TABLE passenger (
id IDENTITY NOT NULL,
passport_id VARCHAR(100),
name VARCHAR(100));

CREATE TABLE ticket (
id IDENTITY NOT NULL,
passenger_id BIGINT NOT NULL,
FOREIGN KEY (passenger_id) REFERENCES passenger(id),
planet_from VARCHAR(100),
CHECK planet_from in ('Mercury', 'Venus', 'Earth', 'Mars', 'Jupiter', 'Saturn', 'Neptune', 'Uranus'),
planet_to VARCHAR(100),
CHECK planet_to in ('Mercury', 'Venus', 'Earth', 'Mars', 'Jupiter', 'Saturn', 'Neptune', 'Uranus'));