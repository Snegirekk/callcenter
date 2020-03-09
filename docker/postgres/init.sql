CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;
COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';

SELECT 'CREATE DATABASE callcenter'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'callcenter')\gexec