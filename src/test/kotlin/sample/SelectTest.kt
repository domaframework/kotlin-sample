package sample

import org.junit.Assert
import org.junit.Test

class SelectTest: AbstractTest() {

    @Test fun test() {
        tm.required {
            val person = dao.selectById(1)
            Assert.assertEquals(Person(1, Name("SMITH"), 10, Address("Tokyo", "Yaesu"), 0), person)
        }
    }
}
