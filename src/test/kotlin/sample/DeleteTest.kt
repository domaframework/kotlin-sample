package sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.Config

@ExtendWith(Env::class)
class DeleteTest(private val config: Config) {

    private val dao: PersonDao = PersonDaoImpl(config)

    @Test
    fun test() {
        config.transactionManager.required {
            val person = dao.selectById(1)
            val (deleted, count) = dao.delete(person)
            assertNotNull(deleted)
            assertEquals(1, count)
        }
    }
}
