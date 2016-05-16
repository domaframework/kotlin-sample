package sample

import org.junit.Assert
import org.junit.Test

class DeleteTest : AbstractTest() {

    @Test fun test() {
        tm.required {
            val person = dao.selectById(1)
            val (entity, count) = dao.delete(person)
            Assert.assertNotNull(entity)
            Assert.assertEquals(1, count)
        }
    }
}
