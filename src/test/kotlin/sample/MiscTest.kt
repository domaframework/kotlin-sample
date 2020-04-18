package sample

import org.junit.Assert
import org.junit.Test

class MiscTest : AbstractTest() {
    @Test
    fun test() {
        Assert.assertEquals("hello hoge", dao.hello("hoge"))
    }
}
