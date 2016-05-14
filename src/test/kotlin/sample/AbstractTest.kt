package sample

import org.junit.After
import org.junit.Before

abstract class AbstractTest {
    val tm = AppConfig.transactionManager
    val dao = DaoFactory.create(PersonDao::class.java)

    @Before fun setUp() {
        tm.required {
            dao.create()
        }
    }

    @After fun tearDown() {
        tm.required {
            dao.drop()
        }
    }
}