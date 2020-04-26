package sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.tx.TransactionManager


@ExtendWith(Env::class)
class SelectTest(private val tm: TransactionManager) {

    private val dao: PersonDao = PersonDaoImpl()

    @Test
    fun test() {
        tm.required {
            val person = dao.selectById(1)
            assertEquals(Person(1, Name("SMITH"), 10, Address("Tokyo", "Yaesu"), 1, Gender.MALE, 0), person)
        }
    }

    @Test fun joinDepartment() {
        tm.required {
            val person = dao.findById(1)
            assertEquals("SMITH", person.name.value)
            assertNotNull(person.department)
            assertEquals(1, person.department!!.id)
        }
    }
}
