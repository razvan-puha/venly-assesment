DROP TABLE IF EXISTS word_relation;

CREATE TABLE word_relation
(
    id       SERIAL PRIMARY KEY,
    word_one   varchar(255) NOT NULL,
    word_two   varchar(255) NOT NULL,
    relation varchar(255) NOT NULL
);