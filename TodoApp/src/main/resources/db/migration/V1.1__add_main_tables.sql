CREATE TABLE IF NOT EXISTS notes (
    id uuid primary key,
    data text,
    header varchar(100),
    created_at timestamp,
    modified_at timestamp
);