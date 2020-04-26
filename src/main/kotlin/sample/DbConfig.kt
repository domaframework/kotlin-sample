package sample

import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.Naming
import org.seasar.doma.jdbc.dialect.H2Dialect
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource
import org.seasar.doma.jdbc.tx.LocalTransactionManager

object DbConfig : Config {

    private val dialect = H2Dialect()

    private val dataSource = LocalTransactionDataSource(
            "jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null)

    internal val localTransaction = dataSource.getLocalTransaction(jdbcLogger)

    private val transactionManager = LocalTransactionManager(localTransaction)

    override fun getDialect() = dialect

    override fun getDataSource() = dataSource

    override fun getTransactionManager() = transactionManager

    override fun getNaming() = Naming.SNAKE_LOWER_CASE

}