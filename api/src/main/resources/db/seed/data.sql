INSERT INTO users (id, email, name, creation_date_time, update_date_time)
VALUES
    (gen_random_uuid(), 'email001', 'name001', now(), now()),
    (gen_random_uuid(), 'email002', 'nema002', now(), now());
