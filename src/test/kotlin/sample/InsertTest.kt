package sample

import org.junit.Assert
import org.junit.Test

class InsertTest : AbstractTest() {

    @Test fun test() {
        tm.required {
            val (entity) = dao.insert(Person(name = Name("WARD"), age = 10))
            val id = entity.id!!
            Assert.assertEquals(dao.selectById(id), entity)
            with(entity) {
                Assert.assertEquals(name, Name("WARD"))
                Assert.assertEquals(age, 10)
            }
        }
    }
}
