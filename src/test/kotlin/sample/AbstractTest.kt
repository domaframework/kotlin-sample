package sample

import org.junit.After
import org.junit.Before

abstract class AbstractTest {
    val config = AppConfig()
    val tm = config.transactionManager
    val dao = DaoFactory.create(PersonDao::class.java, config)

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