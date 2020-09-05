package sample

fun main() {
    val tm = DbConfig.transactionManager
    val scriptDao: ScriptDao = ScriptDaoImpl(DbConfig)
    val personDao: PersonDao = PersonDaoImpl(DbConfig)

    // create initial data
    tm.required {
        scriptDao.create()
    }

    // execute queries
    tm.required {
        val people = personDao.findByDepartmentName("SALES")
        people.forEach {
            println("${it.name} belongs to ${it.department?.name}")
        }
    }
}
