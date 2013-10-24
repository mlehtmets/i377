    INSERT INTO UNIT (id, name, code, super_unit_id) VALUES(1,'CEO','1', null);
    INSERT INTO UNIT (id, name, code, super_unit_id) VALUES(2,'Administration','1-1', 1);
    INSERT INTO UNIT (id, name, code, super_unit_id) VALUES(3,'Legal','1-1-1', 2);
    INSERT INTO UNIT (id, name, code, super_unit_id) VALUES(4,'Archives','1-1-2', 2);
    INSERT INTO UNIT (id, name, code, super_unit_id) VALUES(5,'Production','1-2', 1);
    INSERT INTO UNIT (id, name, code, super_unit_id) VALUES(6,'Sales','2', null);

    ALTER SEQUENCE PUBLIC.SEQ1 RESTART WITH 7