package sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sample.dao.PersonDao
import sample.dao.PersonDaoImpl
import sample.domain.Gender
import sample.domain.Name

@ExtendWith(Env::class)
class SelectTest(config: DbConfig) {
    private val dao: PersonDao = PersonDaoImpl(config)

    @Test
    fun selectById() {
        val person = dao.selectById(1)
        with(person) {
            assertEquals(1, id)
            assertEquals(Name("SMITH"), name)
            assertEquals(10, age)
            assertEquals("Tokyo", city)
            assertEquals("Yaesu", street)
            assertEquals(Gender.MALE, gender)
            assertEquals(0, version)
        }
    }

    @Test
    fun selectByName() {
        val person = dao.selectByName("SMITH")
        with(person) {
            assertEquals(1, id)
            assertEquals(Name("SMITH"), name)
            assertEquals(10, age)
            assertEquals("Tokyo", city)
            assertEquals("Yaesu", street)
            assertEquals(Gender.MALE, gender)
            assertEquals(0, version)
        }
    }

    @Test
    fun selectByCriteriaApi() {
        val persons = dao.findByDepartmentName("ACCOUNTING")
        assertEquals(1, persons.size)
        val person = persons[0]
        assertEquals(Name("SMITH"), person.name)
        assertNotNull(person.department)
        assertEquals(1, person.department?.id)
    }
}
