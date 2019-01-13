import sample.AppConfig
import sample.PersonDaoImpl

fun main(args: Array<String>) {
    val dao = PersonDaoImpl()
    val tm = AppConfig.transactionManager
    tm.required {
        dao.create()
        val person = dao.selectById(1)
        println(person)
    }
}