package sample

import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.JdbcLogger
import org.seasar.doma.jdbc.Naming
import org.seasar.doma.jdbc.Slf4jJdbcLogger
import org.seasar.doma.jdbc.dialect.Dialect
import org.seasar.doma.jdbc.dialect.H2Dialect
import org.seasar.doma.jdbc.tx.LocalTransaction
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource
import org.seasar.doma.jdbc.tx.LocalTransactionManager
import javax.sql.DataSource

class DbConfig(
    private val dialect: Dialect,
    private val dataSource: DataSource,
    private val jdbcLogger: JdbcLogger,
    internal val localTransaction: LocalTransaction
) : Config {

    private val transactionManager = LocalTransactionManager(localTransaction)

    override fun getDialect() = dialect
    override fun getDataSource() = dataSource
    override fun getTransactionManager() = transactionManager
    override fun getJdbcLogger() = jdbcLogger
    override fun getNaming(): Naming = Naming.SNAKE_LOWER_CASE

    companion object {
        fun create(): DbConfig {
            val dialect = H2Dialect()
            val dataSource = LocalTransactionDataSource(
                "jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1",
                "sa",
                null
            )
            val jdbcLogger = Slf4jJdbcLogger()
            val localTransaction = dataSource.getLocalTransaction(jdbcLogger)
            return DbConfig(dialect, dataSource, jdbcLogger, localTransaction)
        }
    }
}
