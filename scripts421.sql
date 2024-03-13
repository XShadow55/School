// добавление условия для студентов 
alter table student add constraint age_check check (age >= 16);
// добавление уникальных имен студентов
alter table student add constraint name_unique unique (name);
// добавление уникальных имен студентов
alter table faculty add constraint faculty_unique unique (name,color);
// добавление уникальных имен и цвета факультета
alter table student alter column name set not null;
// задание условия возроста если он не указан
alter table student alter column age set default 20;
