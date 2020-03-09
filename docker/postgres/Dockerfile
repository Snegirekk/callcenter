FROM postgres:alpine

RUN apk add --no-cache su-exec

COPY docker/postgres/init.sql /docker-entrypoint-initdb.d