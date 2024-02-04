package sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sample.dao.PersonDao
import sample.dao.PersonDaoImpl
import sample.domain.Gender
import sample.domain.Name

@ExtendWith(Env::class)
class UpdateTest(config: DbConfig) {
    private val dao: PersonDao = PersonDaoImpl(config)

    @Test
    fun test() {
        val person = dao.selectById(1)
        person.age?.let { person.age = it + 1 }
        dao.update(person)
        with(person) {
            assertEquals(1, id)
            assertEquals(Name("SMITH"), name)
            assertEquals(11, age)
            assertEquals(Gender.MALE, gender)
        }
    }
}
