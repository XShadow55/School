// Запрос в бд для получения имён студентов
select * from student
select name from student
// Запрос в бд для получения студентов возрост которых больше 10 и меньше 30
select * from student
where age > 10 and age < 30
// Запрос в бд для получения студентов возрост которых меньше их id
select * from student
where age < id
// Запрос в бд для получения студентов в имени которых есть буква о
select * from student
where name like '%о%'
// Запрос в бд для сортировки студентов по возросту
select * from student
order by age
