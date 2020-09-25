package sample

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.internal.util.AssertionUtil.assertEquals
import sample.dao.PersonDao
import sample.dao.PersonDaoImpl
import sample.domain.Gender
import sample.domain.Name
import sample.entity.Person

@ExtendWith(Env::class)
class InsertTest(config: DbConfig) {

    private val dao: PersonDao = PersonDaoImpl(config)

    @Test
    fun test() {
        val entity = dao.insert(
            Person().apply {
                name = Name("WARD")
                age = 10
                city = "Kyoto"
                street = "Kawaramachi"
                departmentId = 1
                gender = Gender.MALE
            }
        )
        val id = entity.id
        val newEntity = dao.selectById(id)
        with(newEntity) {
            assertEquals(Name("WARD"), name)
            assertEquals(10, age)
            assertEquals(Gender.MALE, gender)
            assertEquals(1, version)
        }
    }
}
