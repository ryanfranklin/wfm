#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
    CREATE DATABASE dbaudit;
    GRANT ALL PRIVILEGES ON DATABASE dbaudit TO dbuser;
    CREATE DATABASE dbemployee;
    GRANT ALL PRIVILEGES ON DATABASE dbemployee TO dbuser;
EOSQL
