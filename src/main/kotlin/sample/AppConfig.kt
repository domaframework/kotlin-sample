package sample

import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.Naming
import org.seasar.doma.jdbc.dialect.H2Dialect
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource
import org.seasar.doma.jdbc.tx.LocalTransactionManager

object AppConfig : Config {

    private val dialect = H2Dialect()

    private val dataSource = LocalTransactionDataSource(
            "jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null)

    private val transactionManager = LocalTransactionManager(
            dataSource.getLocalTransaction(jdbcLogger))

    override fun getDialect() = dialect

    override fun getDataSource() = dataSource

    override fun getTransactionManager() = transactionManager

    override fun getNaming() = Naming.SNAKE_LOWER_CASE
}