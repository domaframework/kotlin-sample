package sample

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(Env::class)
class DeleteTest(private val config: DbConfig) {

    private val dao: PersonDao = PersonDaoImpl(config)

    @Test
    fun test() {
        config.transactionManager.required {
            val person = dao.selectById(1)
            val deleted = dao.delete(person)
            assertNotNull(deleted)
        }
    }
}
