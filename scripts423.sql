// Объединение по столбцу faculty_id имени, возроста студента и названия факультета
select s."name",s."age",f."name" from student s join faculty f on (s.faculty_id = f."id");
// Объединение по столбцу id студента и его аватара
select * from student s join avatar a on (s.id = a.student_id);
