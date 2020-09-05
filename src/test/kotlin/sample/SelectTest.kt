package sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(Env::class)
class SelectTest(private val config: DbConfig) {

    private val dao: PersonDao = PersonDaoImpl(config)

    @Test
    fun selectBySqlFile() {
        config.transactionManager.required {
            val person = dao.selectById(1)
            assertEquals(
                Person(
                    1,
                    Name("SMITH"),
                    10,
                    Address("Tokyo", "Yaesu"),
                    1,
                    Gender.MALE,
                    0
                ),
                person
            )
        }
    }

    @Test
    fun selectBySqlAnnotation() {
        config.transactionManager.required {
            val person = dao.selectByName("SMITH")
            assertEquals(
                Person(
                    1,
                    Name("SMITH"),
                    10,
                    Address("Tokyo", "Yaesu"),
                    1,
                    Gender.MALE,
                    0
                ),
                person
            )
        }
    }

    @Test
    fun selectByCriteriaApi() {
        config.transactionManager.required {
            val persons = dao.findByDepartmentName("ACCOUNTING")
            assertEquals(1, persons.size)
            val person = persons[0]
            assertEquals("SMITH", person.name.value)
            assertNotNull(person.department)
            assertEquals(1, person.department?.id)
        }
    }
}
