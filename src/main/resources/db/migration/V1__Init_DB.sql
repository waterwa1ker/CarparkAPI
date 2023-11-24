create table public.Car(
    id int primary key generated by default as identity,
    numberplate varchar(10) UNIQUE,
    model varchar(100),
    year_of_manufacture int check (year_of_manufacture > 0),
    mileage int check ( mileage >= 0 ),
    car_type varchar(50)
);