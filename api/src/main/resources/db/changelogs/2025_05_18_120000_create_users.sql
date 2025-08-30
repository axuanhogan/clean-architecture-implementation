CREATE TABLE IF NOT EXISTS users (
    id uuid not null,
    email varchar(64),
    name varchar(16),
    creation_date_time timestamp(6) with time zone,
    update_date_time timestamp(6) with time zone,
    primary key (id)
);
