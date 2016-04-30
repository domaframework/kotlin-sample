package sample

import org.junit.Assert
import org.junit.Test

class InsertTest : AbstractTest() {

    @Test fun test() {
        tm.required {
            val result = dao.insert(Person(name = Name("WARD"), age = 10))
            val id = result.entity.id!!
            Assert.assertEquals(dao.selectById(id), result.entity)
            with(result.entity) {
                Assert.assertEquals(name, Name("WARD"))
                Assert.assertEquals(age, 10)
            }
        }
    }
}
