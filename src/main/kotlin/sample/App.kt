package sample

import sample.dao.PersonDao
import sample.dao.PersonDaoImpl
import sample.dao.ScriptDao
import sample.dao.ScriptDaoImpl

fun main() {
    val config = DbConfig.create()
    val scriptDao: ScriptDao = ScriptDaoImpl(config)
    val personDao: PersonDao = PersonDaoImpl(config)

    // create initial data
    config.transactionManager.required {
        scriptDao.create()
    }

    // execute queries
    config.transactionManager.required {
        val people = personDao.findByDepartmentName("SALES")
        people.forEach {
            println("${it.name} belongs to ${it.department?.name}")
        }
    }
}
