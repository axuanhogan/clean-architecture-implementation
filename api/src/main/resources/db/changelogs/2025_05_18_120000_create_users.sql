CREATE TABLE IF NOT EXISTS users (
    id uuid not null,
    name varchar(64),
    create_date_time timestamp(6) with time zone,
    update_date_time timestamp(6) with time zone,
    primary key (id)
);
