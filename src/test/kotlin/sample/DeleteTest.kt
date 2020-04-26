package sample

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.seasar.doma.jdbc.tx.TransactionManager

@ExtendWith(Env::class)
class DeleteTest(private val tm: TransactionManager) {

    private val dao: PersonDao = PersonDaoImpl()

    @Test
    fun test() {
        tm.required {
            val person = dao.selectById(1)
            val (entity, count) = dao.delete(person)
            assertNotNull(entity)
            assertEquals(1, count)
        }
    }
}
