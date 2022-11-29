use test;

create table if not exists
    STUDENT
(
    ID         int          not null auto_increment primary key,
    CLASS_NAME varchar(20)  not null,
    USERNAME   varchar(30)  not null unique,
    PASSWORD   varchar(128) not null,
    EMAIL      varchar(30)  not null,
    PHONE      varchar(15)  not null
) charset = utf8mb4;

create table if not exists
    TEACHER
(
    ID       int          not null auto_increment primary key,
    USERNAME varchar(20)  not null unique,
    PASSWORD varchar(128) not null
) charset = utf8mb4;

create table if not exists
    `STUDENT_TEACHER`
(
    ID           int     not null auto_increment primary key,
    STUDENT_ID   int     not null references STUDENT (`ID`),
    TEACHER_ID   int     not null references TEACHER (`ID`),
    IS_CONFIRMED boolean not null default false
) charset = utf8mb4;

create table if not exists
    `SCORE`
(
    ID                 int    not null auto_increment primary key,
    STUDENT_TEACHER_ID int    not null references STUDENT_TEACHER (`ID`),
    USUAL_GRADE        double null default null,
    MID_EXAM_GRADE     double null default null,
    FINAL_EXAM_GRADE   double null default null,
    EXPERIMENT_GRADE   double null default null
) charset = utf8mb4;

-- 连接中一定要指定时区
create table if not exists
    `ASSIGNMENT`
(
    ID       int       not null auto_increment primary key,
    QUESTION text      not null,
    DEADLINE timestamp null default null comment 'null,代表作业永远不会过期，没有截止时间'
) charset = utf8mb4;

create table if not exists
    `STUDENT_TEACHER_ASSIGNMENT`
(
    ID                 int    not null auto_increment primary key,
    ASSIGNMENT_ID      int    not null references ASSIGNMENT (`ID`),
    STUDENT_TEACHER_ID int    not null references STUDENT_TEACHER (`ID`),
    ANSWER             text   not null,
    SCORE              double null default null
) charset = utf8mb4;