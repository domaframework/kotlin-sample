package sample

import org.junit.Assert
import org.junit.Test

class DeleteTest : AbstractTest() {

    @Test fun test() {
        tm.required {
            val person = dao.selectById(1)
            val result = dao.delete(person)
            Assert.assertEquals(1, result.count)
        }
    }
}
