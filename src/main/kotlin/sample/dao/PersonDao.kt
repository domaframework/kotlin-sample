package sample.dao

import org.seasar.doma.Dao
import org.seasar.doma.Select
import org.seasar.doma.Sql
import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.criteria.KEntityql
import sample.domain.Name
import sample.entity.Department_
import sample.entity.Person
import sample.entity.Person_

@Dao
interface PersonDao {

    private val entityql
        get() = KEntityql(Config.get(this))

    @Sql("select * from person where id = /*id*/0")
    @Select
    fun selectById(id: Int): Person

    @Sql("select * from person where name = /*name*/0")
    @Select
    fun selectByName(name: String): Person

    fun findByDepartmentName(departmentName: String): List<Person> {
        val p = Person_()
        val d = Department_()

        return entityql.from(p).innerJoin(d) {
            eq(p.departmentId, d.id)
        }.where {
            eq(d.name, Name(departmentName))
        }.associate(p, d) { person, department ->
            person.department = department
        }.fetch()
    }

    fun insert(person: Person): Person {
        val p = Person_()
        val result = entityql.insert(p, person).execute()
        return result.entity
    }

    fun update(person: Person): Person {
        val p = Person_()
        val result = entityql.update(p, person).execute()
        return result.entity
    }

    fun delete(person: Person): Person {
        val p = Person_()
        val result = entityql.delete(p, person).execute()
        return result.entity
    }
}
