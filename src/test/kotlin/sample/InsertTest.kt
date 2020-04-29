package sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.Config

@ExtendWith(Env::class)
class InsertTest(private val config: Config) {

    private val dao: PersonDao = PersonDaoImpl(config)

    @Test
    fun test() {
        config.transactionManager.required {
            val address = Address(city = "Kyoto", street = "Kawaramachi")
            val (entity) = dao.insert(
                    Person(name = Name("WARD"),
                            age = 10,
                            address = address,
                            departmentId = 1,
                            gender = Gender.MALE))
            val id = entity.id!!
            assertEquals(dao.selectById(id), entity)
            with(entity) {
                assertEquals(name, Name("WARD"))
                assertEquals(age, 10)
                assertEquals(gender, Gender.MALE)
                assertEquals(version, 1)
            }
        }
    }
}
