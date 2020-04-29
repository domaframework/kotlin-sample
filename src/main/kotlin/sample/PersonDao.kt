package sample

import org.seasar.doma.Dao
import org.seasar.doma.Delete
import org.seasar.doma.Insert
import org.seasar.doma.Select
import org.seasar.doma.Sql
import org.seasar.doma.Update
import org.seasar.doma.criteria.entityql
import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.Result

@Dao
interface PersonDao {

    private val config
        get() = Config.get(this)

    @Select
    fun selectById(id: Int): Person

    @Sql("select * from person where name = /*name*/0")
    @Select
    fun selectByName(name: String): Person

    fun findByDepartmentName(departmentName: String): List<Person> {
        val query = entityql {
            from(::Person_) { p ->
                val d = innerJoin(::Department_) { d ->
                    p.id eq d.id
                }
                where {
                    d.name eq departmentName
                }
                associate(p, d) { person, department ->
                    person.department = department
                }
            }
        }
        return query.execute(config)
    }

    @Insert
    fun insert(person: Person): Result<Person>

    @Update
    fun update(person: Person): Result<Person>

    @Delete
    fun delete(person: Person): Result<Person>
}
