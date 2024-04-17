# --- !Ups

CREATE TABLE "users" (
    "id" INT AUTO_INCREMENT PRIMARY KEY,
    "name" VARCHAR NOT NULL,
    "age" VARCHAR NOT NULL
);

# --- !Downs

DROP TABLE "users";
