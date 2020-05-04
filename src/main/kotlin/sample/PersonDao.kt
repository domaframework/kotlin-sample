package sample

import org.seasar.doma.BatchInsert
import org.seasar.doma.Dao
import org.seasar.doma.Delete
import org.seasar.doma.Insert
import org.seasar.doma.Select
import org.seasar.doma.Sql
import org.seasar.doma.Update
import org.seasar.doma.jdbc.BatchResult
import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.Result
import org.seasar.doma.jdbc.criteria.Entityql

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
        val p = Person_()
        val d = Department_()

        val stmt = Entityql.from(p).innerJoin(d) {
            it.eq(p.departmentId, d.id)
        }.where {
            it.eq(d.name, departmentName)
        }.associate(p, d) { person, department ->
            person.department = department
        }
        return stmt.execute(config)
    }

    @Insert
    fun insert(person: Person): Result<Person>

    @Update
    fun update(person: Person): Result<Person>

    @Delete
    fun delete(person: Person): Result<Person>
}
