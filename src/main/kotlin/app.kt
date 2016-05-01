
import sample.AppConfig
import sample.DaoFactory
import sample.PersonDao

fun main(args: Array<String>) {
    val dao = DaoFactory.create(PersonDao::class.java, AppConfig)
    val tm = AppConfig.transactionManager
    tm.required {
        dao.create()
        val person = dao.selectById(1)
        println(person)
    }
}