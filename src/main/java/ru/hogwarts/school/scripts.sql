select * from student;
select * from faculty;
select * from student where age between 10 and 20;
select name from student;
select * from student where name like '%о%';
select * from student where age < student.id;
select * from student order by age;
select s.*  from student as s,faculty as f where s.faculty_id = f.id
                                             and s.age=11;

