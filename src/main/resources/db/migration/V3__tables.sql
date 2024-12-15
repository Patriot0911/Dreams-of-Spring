CREATE SEQUENCE users_seq
    START WITH 1
    INCREMENT BY 1 NOMAXVALUE
    NOCYCLE;

CREATE TABLE users
(
    id   NUMBER(10)    NOT NULL,
    name VARCHAR2(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE
    OR REPLACE TRIGGER create_users_trigger
    BEFORE INSERT
    ON users
    FOR EACH ROW
    WHEN (NEW.id IS NULL)
BEGIN
    SELECT users_seq.NEXTVAL
    INTO :NEW.id
    FROM DUAL;
END;
/

ALTER TABLE tasks
    ADD user_id NUMBER(10);

ALTER TABLE tasks
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES users (id)
            ON DELETE SET NULL;

INSERT INTO users (name)
SELECT 'Mr. Anderson'

FROM dual;

CREATE TABLE fbi
(
    id      NUMBER(10) NOT NULL,
    user_id NUMBER(10),
    PRIMARY KEY (id)
);

ALTER TABLE fbi
    ADD CONSTRAINT fk_fbi_id FOREIGN KEY (user_id)
        REFERENCES users (id)
            ON DELETE SET NULL;


INSERT INTO fbi (id, user_id)
VALUES (1, (SELECT id FROM users WHERE name = 'Mr. Anderson'));


INSERT INTO fbi (id, user_id)
VALUES (2, (SELECT id FROM users WHERE name = 'Mr. Anderson'));


INSERT INTO fbi (id, user_id)
VALUES (3, (SELECT id FROM users WHERE name = 'Mr. Anderson'));

UPDATE tasks
SET user_id = (SELECT id FROM users WHERE name = 'Mr. Anderson')
where TASKS.user_id is null;

CREATE OR REPLACE PROCEDURE clear_schema IS
BEGIN
    EXECUTE IMMEDIATE 'DROP TABLE "flyway_schema_history" CASCADE CONSTRAINTS PURGE';
    EXECUTE IMMEDIATE 'DROP TABLE TASKS CASCADE CONSTRAINTS PURGE';
    EXECUTE IMMEDIATE 'DROP TABLE USERS CASCADE CONSTRAINTS PURGE';
    EXECUTE IMMEDIATE 'DROP TABLE FBI CASCADE CONSTRAINTS PURGE';

    EXECUTE IMMEDIATE 'DROP SEQUENCE MY_TASKS_ID_SEQ';
    EXECUTE IMMEDIATE 'DROP SEQUENCE USERS_SEQ';
END;
/
