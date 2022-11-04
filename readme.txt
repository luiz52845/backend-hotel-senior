docker pull postgres
docker run --name postgres-db -e POSTGRES_PASSWORD=123 -p 5432:5432 -d postgres

DB: postgres-db
user: postgres
password:123
