DROP TABLE if EXISTS member CASCADE;
CREATE TABLE member
(
    id      bigint GENERATED BY DEFAULT AS IDENTITY,
    name    VARCHAR(255),
    PRIMARY KEY (id)
);

