package sample

import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.seasar.doma.jdbc.tx.TransactionManager

internal class Env :
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

//    private val config = object : Config {
//        private val dataSource = createDataSource()
//        val localTransaction = dataSource.getLocalTransaction(jdbcLogger)
//        private val transactionManager = LocalTransactionManager(localTransaction)
//        private val dialect = H2Dialect()
//
//        override fun getDataSource(): DataSource {
//            return dataSource
//        }
//
//        override fun getDialect(): Dialect {
//            return dialect
//        }
//
//        override fun getTransactionManager(): TransactionManager {
//            return transactionManager
//        }
//
//        override fun getNaming(): Naming {
//            return Naming.SNAKE_UPPER_CASE
//        }
//
//        private fun createDataSource(): LocalTransactionDataSource {
//            val dataSource = SimpleDataSource()
//            dataSource.url = "jdbc:h2:mem:doma_it;DB_CLOSE_DELAY=-1"
//            dataSource.user = "sa"
//            dataSource.password = ""
//            return LocalTransactionDataSource(dataSource)
//        }
//    }

    override fun beforeTestExecution(context: ExtensionContext?) {
        DbConfig.transactionManager.required {
            DbConfig.dataSource.connection.use { con ->
                con.createStatement().use { stmt ->
                    stmt.execute(initSql)
                }
            }
        }
        DbConfig.localTransaction.begin()
    }

    override fun afterTestExecution(context: ExtensionContext?) {
        DbConfig.localTransaction.rollback()
        DbConfig.transactionManager.required {
            DbConfig.dataSource.connection.use { con ->
                con.createStatement().use { stmt ->
                    stmt.execute("DROP ALL OBJECTS")
                }
            }
        }
    }

    override fun supportsParameter(
            parameterContext: ParameterContext?,
            extensionContext: ExtensionContext?
    ): Boolean =
            parameterContext!!.parameter.type === TransactionManager::class.java


    override fun resolveParameter(
            parameterContext: ParameterContext?,
            extensionContext: ExtensionContext?
    ): Any? {
        return DbConfig.transactionManager
    }

    private val initSql = """
        create table department(
            id int not null identity primary key,
            name varchar(20),
            version int not null
        );
    
        create table person(
            id int not null identity primary key,
            name varchar(20),
            age int,
            city varchar(20) not null,
            street varchar(20) not null,
            department_id int not null,
            gender varchar(1) not null,
            version int not null,
            constraint fk_department_id foreign key(department_id) references department(id)
        );
    
        insert into department (id, name, version) values(1, 'ACCOUNTING', 0);
        insert into department (id, name, version) values(2, 'SALES', 0);
    
        insert into person (id, name, age, city, street, department_id, gender, version) values(1, 'SMITH', 10, 'Tokyo', 'Yaesu', 1, '0', 0);
        insert into person (id, name, age, city, street, department_id, gender, version) values(2, 'ALLEN', 20, 'Kyoto', 'Karasuma', 2, '1', 0);
        """.trimIndent()
}
