
import sample.AppConfig
import sample.DaoFactory
import sample.PersonDao

fun main(args: Array<String>) {
    val config = AppConfig()
    val dao = DaoFactory.create(PersonDao::class.java, config)
    val tm = config.transactionManager
    tm.required {
        dao.create()
        val person = dao.selectById(1)
        println(person)
    }
}