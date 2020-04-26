package sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(Env::class)
class UpdateTest {

    private val dao: PersonDao = PersonDaoImpl()

    @Test
    fun test() {
        val person = dao.selectById(1)
        val newPerson = person.copy(age = person.age?.let { it + 1 })
        val (entity) = dao.update(newPerson)
        with(entity) {
            assertEquals(id, 1)
            assertEquals(name, Name("SMITH"))
            assertEquals(age, 11)
            assertEquals(gender, Gender.MALE)
        }
    }
}
