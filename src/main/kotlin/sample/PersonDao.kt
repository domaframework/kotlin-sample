package sample

import org.seasar.doma.Dao
import org.seasar.doma.Delete
import org.seasar.doma.Insert
import org.seasar.doma.Script
import org.seasar.doma.Select
import org.seasar.doma.Sql
import org.seasar.doma.Update
import org.seasar.doma.criteria.entityql
import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.Result

@Dao(config = DbConfig::class)
interface PersonDao {

    @Sql("""
    """)
    @Script
    fun create()

    @Sql("""
    drop table person;
    drop table department;
    """)
    @Script
    fun drop()

    @Select
    fun selectById(id: Int): Person

    fun findById(id: Int): Person {
        val query = entityql {
            from(::_Person) { p ->
                val d = innerJoin(::_Department) { d ->
                    p.id eq d.id
                }
                where {
                    p.id eq id
                }
                associate(p, d) { person, department ->
                    person.department = department
                }
            }
        }
        return query.execute(Config.get(this)).first()
    }

    @Insert
    fun insert(person: Person): Result<Person>

    @Update
    fun update(person: Person): Result<Person>

    @Delete
    fun delete(person: Person): Result<Person>

}
