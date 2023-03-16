CREATE TABLE IF NOT EXISTS todo_list (
    id uuid primary key,
    data varchar(50),
    is_done boolean default false,
    created_at timestamp default now(),
    modified_at timestamp default now()
);