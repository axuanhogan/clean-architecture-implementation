INSERT INTO users (id, name, create_date_time, update_date_time)
VALUES
    (gen_random_uuid(), 'test001', now(), now()),
    (gen_random_uuid(), 'test002', now(), now());
