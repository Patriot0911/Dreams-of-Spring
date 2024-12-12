CREATE SEQUENCE my_tasks_id_seq
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE
    NOCYCLE;

CREATE TABLE tasks (
    id NUMBER(10) NOT NULL,
    title VARCHAR2(255) NOT NULL,
    description VARCHAR2(4000),
    priority NUMBER(3),
    task_date DATE,
    completed NUMBER(1) DEFAULT 0 NOT NULL,
    PRIMARY KEY (id)
);

CREATE OR REPLACE TRIGGER my_tasks_id_trigger
    BEFORE INSERT ON tasks
    FOR EACH ROW
    WHEN (NEW.id IS NULL)
BEGIN
    SELECT my_tasks_id_seq.NEXTVAL
    INTO :NEW.id
    FROM DUAL;
END;
/
