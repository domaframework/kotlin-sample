package sample;

import org.seasar.doma.*;
import org.seasar.doma.jdbc.Result;

@Dao(config = AppConfig.class)
public interface PersonDao {

    @Script
    void create();

    @Script
    void drop();

    @Select
    Person selectById(Integer id);

    @Select
    PersonDepartment selectWithDeparmentById(Integer id);

    @Insert
    Result<Person> insert(Person person);

    @Update
    Result<Person> update(Person person);

    @Delete
    Result<Person> delete(Person person);
}
