package sample

import org.seasar.doma.Dao
import org.seasar.doma.Script
import org.seasar.doma.Sql

@Dao
interface ScriptDao {

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
            gender varchar(1) not null,
            version int not null,
            constraint fk_department_id foreign key(department_id) references department(id)
        );
    
        insert into department (id, name, version) values(1, 'ACCOUNTING', 0);
        insert into department (id, name, version) values(2, 'SALES', 0);
    
        insert into person (id, name, age, city, street, department_id, gender, version) values(1, 'SMITH', 10, 'Tokyo', 'Yaesu', 1, '0', 0);
        insert into person (id, name, age, city, street, department_id, gender, version) values(2, 'ALLEN', 20, 'Kyoto', 'Karasuma', 2, '1', 0);
        """)
    @Script
    fun create()

    @Sql("drop all objects;")
    @Script
    fun drop()
}