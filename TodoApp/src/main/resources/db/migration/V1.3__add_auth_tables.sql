CREATE TABLE IF NOT EXISTS users (
    id uuid not null primary key,
    email varchar(100) not null unique,
    first_name varchar(50),
    last_name varchar(50),
    password varchar(100),
    last_access_date timestamp,
    created_at timestamp default now(),
    modified_at timestamp default now()
);

CREATE TABLE IF NOT EXISTS roles (
    id uuid not null primary key,
    code varchar(50) not null,
    name varchar(50) not null,
    created_at timestamp default now(),
    modified_at timestamp default now()
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id uuid not null
        constraint users_roles_user_id_fk
            references users,
    role_id uuid not null
        constraint users_roles_role_id_fk
            references roles
);

INSERT INTO roles (id, name, code) VALUES
     ('016f534f-8bf2-4a96-a0e7-d3b5ead834c8', 'Administrator', 'ROLE_ADMIN'),
     ('193ef1c5-be9c-49c8-81a8-cb0c9fb1df11', 'User', 'ROLE_USER');

INSERT INTO users (id, email, first_name, last_name, password) VALUES
     ('ea34ad35-9067-494e-b544-667d14cefa4d', 'admin@mail.ru', 'admin', 'admin', '$2a$12$gAGcohccr.U20sV28zwjveeSuXwmQRml3GBQ/vXCg4rv7Mtoo8PQW');

INSERT INTO users_roles (user_id, role_id) VALUES
    ('ea34ad35-9067-494e-b544-667d14cefa4d', '016f534f-8bf2-4a96-a0e7-d3b5ead834c8');