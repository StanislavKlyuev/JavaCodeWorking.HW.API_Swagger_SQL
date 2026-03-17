select *
from student;

select *
from student
where age between 20 and 22;

select name, surname
from student;

select *
from student
where surname ilike '%О%';

select *
from student
where age < id;

select *
from student
ORDER BY age;

select *
from faculty;

select *
from faculty
where color ilike '%син%';

select s.id, s.name, s.surname
from student as s, faculty as f
where s.faculty_id = f.id
and f.id = 3;

select f.id, f.name, f.color
from student as s, faculty as f
where s.faculty_id = f.id
  and s.id = 5;
