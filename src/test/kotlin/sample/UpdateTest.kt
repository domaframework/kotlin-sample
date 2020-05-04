package sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.Config

@ExtendWith(Env::class)
class UpdateTest(config: Config) {

    private val dao: PersonDao = PersonDaoImpl(config)

    @Test
    fun test() {
        val person = dao.selectById(1)
        val copied = person.copy(age = person.age?.let { it + 1 })
        val (newPerson) = dao.update(copied)
        with(newPerson) {
            assertEquals(id, 1)
            assertEquals(name, Name("SMITH"))
            assertEquals(age, 11)
            assertEquals(gender, Gender.MALE)
        }
    }
}
