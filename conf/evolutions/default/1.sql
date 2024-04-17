# --- !Ups

CREATE TABLE "users" (
    "id" INT AUTO_INCREMENT PRIMARY KEY,
    "name" VARCHAR NOT NULL,
    "age" INT NOT NULL
);

# --- !Downs

DROP TABLE "users";
