create table human(
    id serial PRIMARY KEY,
    name_human text,
    age integer,
    has_license boolean,
    car_id integer references car(id)
);

create table car(
    id serial PRIMARY KEY,
    brend text,
    model text,
    cost integer

);