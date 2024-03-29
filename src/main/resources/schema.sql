use spm;

create table if not exists
    STUDENT
(
    ID         int          not null auto_increment primary key,
    CLASS_NAME varchar(20)  not null,
    USERNAME   varchar(30)  not null unique,
    PASSWORD   varchar(128) not null,
    EMAIL      varchar(30)  not null unique,
    PHONE      varchar(15)  not null unique
) charset = utf8mb4;

-- 注意要指定连接的时区
create table if not exists
    TEACHER
(
    ID              int          not null auto_increment primary key,
    USERNAME        varchar(20)  not null unique,
    PASSWORD        varchar(128) not null,
    COURSE_TIME     varchar(150) not null comment '上课开始时间',
    COURSE_LOCATION varchar(20)  not null
) charset = utf8mb4;

create table if not exists
    `STUDENT_TEACHER`
(
    ID           int     not null auto_increment primary key,
    STUDENT_ID   int     not null references STUDENT (`ID`),
    TEACHER_ID   int     not null references TEACHER (`ID`),
    IS_CONFIRMED boolean not null default false,
    constraint STUDENT_TEACHER_UNIQUE
        unique (STUDENT_ID, TEACHER_ID),
    constraint STUDENT_TEACHER_UNIQUE1
        unique (STUDENT_ID)
) charset = utf8mb4;

create table if not exists
    `SCORE`
(
    ID                 int    not null auto_increment primary key,
    STUDENT_TEACHER_ID int    not null unique references STUDENT_TEACHER (`ID`),
    USUAL_GRADE        double null default null check ( USUAL_GRADE between 0 and 100),
    MID_EXAM_GRADE     double null default null check ( MID_EXAM_GRADE between 0 and 100),
    FINAL_EXAM_GRADE   double null default null check ( FINAL_EXAM_GRADE between 0 and 100),
    EXPERIMENT_GRADE   double null default null check ( EXPERIMENT_GRADE between 0 and 100)
) charset = utf8mb4;

-- 连接中一定要指定时区
create table if not exists
    `ASSIGNMENT`
(
    ID       int       not null auto_increment primary key,
    QUESTION text      not null,
    DEADLINE timestamp null     default null comment 'null,代表作业永远不会过期，没有截止时间',
    BY_GROUP boolean   not null default false comment '是否是小组作业；小组作业则是ture,否则是false'
) charset = utf8mb4;

create table if not exists
    `STUDENT_TEACHER_ASSIGNMENT`
(
    ID                 int    not null auto_increment primary key,
    ASSIGNMENT_ID      int    not null references ASSIGNMENT (`ID`),
    STUDENT_TEACHER_ID int    not null references STUDENT_TEACHER (`ID`),
    ANSWER             text   ,
    SCORE              double null default null,
    constraint STUDENT_TEACHER_ASSIGNMENT_UNIQUE
        unique (ASSIGNMENT_ID, STUDENT_TEACHER_ID)
) charset = utf8mb4;

-- 选课之后，创建的小组，LEADER_ID 记录组长的id
create table if not exists
    `GROUP01`
(
    ID        int         not null auto_increment primary key,
    NAME      varchar(30) not null unique,
    LEADER_ID int         not null comment '当前课程中小组的创建人or组长' unique references `STUDENT_TEACHER` (`ID`)
) charset = utf8mb4;

-- 选课之后，记录每个小组内的成员。
-- 成员可以包括也可以不包括组长。
-- 考虑一个人不能在两个组，可以把组长包括在成员表中，
-- 利用sql的约束条件，去防止一个人既是A的组长，又是B的成员
create table if not exists
    `GROUP_MEMBER`
(
    ID                 int not null auto_increment primary key,
    GROUP_ID           int not null references `GROUP01` (`ID`),
    STUDENT_TEACHER_ID int not null unique references `STUDENT_TEACHER` (`ID`)
) charset = utf8mb4;