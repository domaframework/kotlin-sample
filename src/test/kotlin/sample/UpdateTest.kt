package sample

import org.junit.Assert
import org.junit.Test

class UpdateTest: AbstractTest() {

    @Test fun test() {
        tm.required {
            val person = dao.selectById(1)
            val newPerson = person.copy(age = person.age?.let { it + 1 })
            val result = dao.update(newPerson)
            with(result.entity) {
                Assert.assertEquals(id, 1)
                Assert.assertEquals(name, Name("SMITH"))
                Assert.assertEquals(age, 11)
            }
        }
    }
}
