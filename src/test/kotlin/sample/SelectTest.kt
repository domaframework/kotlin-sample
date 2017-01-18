package sample

import org.junit.Assert
import org.junit.Test

class SelectTest: AbstractTest() {

    @Test fun test() {
        tm.required {
            val person = dao.selectById(1)
            Assert.assertEquals(Person(1, Name("SMITH"), 10, Address("Tokyo", "Yaesu"), 1, 0), person)
        }
    }

    @Test fun joinDepartment() {
        tm.required {
            val person = dao.selectWithDeparmentById(1)
            Assert.assertEquals(PersonDepartment(1, Name("SMITH"), 1, Name("ACCOUNTING")), person)
        }

    }
}
