package sample

import org.seasar.doma.*
import org.seasar.doma.experimental.Sql
import org.seasar.doma.jdbc.Result

@Dao(config = AppConfig::class)
interface PersonDao {

    @Sql("""
    create table department(
        id int not null identity primary key,
        name varchar(20),
        version int not null
    );

    create table person(
        id int not null identity primary key,
        name varchar(20),
        age int,
        city varchar(20) not null,
        street varchar(20) not null,
        department_id int not null,
        version int not null,
        constraint fk_department_id foreign key(department_id) references department(id)
    );

    insert into department (id, name, version) values(1, 'ACCOUNTING', 0);
    insert into department (id, name, version) values(2, 'SALES', 0);

    insert into person (id, name, age, city, street, department_id, version) values(1, 'SMITH', 10, 'Tokyo', 'Yaesu', 1, 0);
    insert into person (id, name, age, city, street, department_id, version) values(2, 'ALLEN', 20, 'Kyoto', 'Karasuma', 2, 0);
    """)
    @Script
    fun create()

    @Sql("""
    drop table person;
    drop table department;
    """)
    @Script
    fun drop()

    @Sql("""
    select * from person where id = /*id*/0
    """)
    @Select
    fun selectById(id: Int?): Person

    @Sql("""
    select
        p.id,
        p.name,
        d.id as department_id,
        d.name as department_name
    from
        person p
        inner join
        department d
        on (p.id = d.id)
    where
        p.id = /*id*/0
    """)
    @Select
    fun selectWithDepartmentById(id: Int?): PersonDepartment

    @Insert
    fun insert(person: Person): Result<Person>

    @Update
    fun update(person: Person): Result<Person>

    @Delete
    fun delete(person: Person): Result<Person>
}
