-- 脚本创建测试数据
-- 学生和老师的数据见下面
--
-- 选课：AA,BB,CC都进行了选课，选的是TA老师。只有BB和CC的选课被确认了。
-- 成绩只有CC和BB的成绩，CC不及格，BB是及格的
-- 分组： BB和CC是同一组，BB是组长
-- 作业： BB提交了个人作业，CC没提交；小组作业被提交了。

use test;

insert into STUDENT
values (1, '202001', 'AA', '123456', 'AA@qq.mail', '12345678901'), -- 选课了，未确认
       (2, '202001', 'BB', '123456', 'BB@qq.mail', '22345678901'), -- 选课了，确认了
       (3, '202001', 'CC', '123456', 'CC@qq.mail', '32345678901'), -- 选课了，确认了
       (4, '202002', 'DD', '123456', 'DD@qq.mail', '42345678901'),
       (5, '202002', 'EE', '123456', 'EE@qq.mail', '52345678901'),
       (6, '202003', 'FF', '123456', 'FF@qq.mail', '62345678901'),
       (7, '202003', 'GG', '123456', 'GG@qq.mail', '72345678901');

insert into TEACHER
values (1, 'TA', '123456', '周一（8,9）', '一教503'), -- 有一名确认的，一名已确认的
       (2, 'TB', '123456', '周二（3,4）', '一教503'), -- 一名确认的
       (3, 'TC', '123456', '周一（3,4）', '一教504'),
       (4, 'TD', '123456', '周五（4,5）', '一教503'),
       (5, 'TE', '123456', '周三（2,3）', '一教505'),
       (6, 'TF', '123456', '周三（8,9）', '一教503');

insert into STUDENT_TEACHER
values (1, 1, 1, 0),
       (2, 2, 1, 1),
       (3, 3, 1, 1);

insert into SCORE
values
    -- CC在TA老师下的成绩
    (1, 3, 12, 13, 14, 14),
    -- BB在TA老师下的成绩
    (2, 2, 100, 100, 100, 100);

-- 小组是‘小组一号’
-- 组长是 BB,成员只有CC。
insert into GROUP01
values (1, '小组一号', 2);

insert into GROUP_MEMBER
values (1, 1, 2), -- 组长
       (2, 1, 3);


-- 作业
insert into ASSIGNMENT
values (1, '个人测试问题', '2022-12-18 17:50:30', 0), -- 有截止日期的个人作业
       (2, '小组测试问题', null, 1);
-- 无截止日期的小组作业

-- 布置作业给BB和CC，
-- 个人作业，CC未作答
-- 小组作业，已经被答，具体答题人，不可知。数据库只记录答题人为BB
insert into STUDENT_TEACHER_ASSIGNMENT
values (1, 1, 2, '个人测试题的答案', null), -- BB 的作业1的答案，未打分
       (2, 2, 2, '组长B记录被记录为答题人，所有小组内的人都可以看到题目已经被作答', 56); -- BB 的小组作业2的答案，已经打分
