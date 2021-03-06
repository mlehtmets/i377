   DROP SCHEMA PUBLIC CASCADE

    CREATE SEQUENCE seq1 AS INTEGER START WITH 1

    CREATE TABLE unit (
       id BIGINT NOT NULL PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       code VARCHAR(255) UNIQUE NOT NULL,
       super_unit_id BIGINT,
       FOREIGN KEY (super_unit_id)
          REFERENCES unit ON DELETE CASCADE
    );