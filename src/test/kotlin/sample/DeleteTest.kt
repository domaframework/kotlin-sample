package sample

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import sample.dao.PersonDao
import sample.dao.PersonDaoImpl

@ExtendWith(Env::class)
class DeleteTest(config: DbConfig) {
    private val dao: PersonDao = PersonDaoImpl(config)

    @Test
    fun test() {
        val person = dao.selectById(1)
        val deleted = dao.delete(person)
        assertNotNull(deleted)
    }
}
