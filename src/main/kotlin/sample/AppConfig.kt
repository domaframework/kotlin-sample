package sample

import org.seasar.doma.jdbc.Config
import org.seasar.doma.jdbc.dialect.Dialect
import org.seasar.doma.jdbc.dialect.H2Dialect
import org.seasar.doma.jdbc.tx.LocalTransactionDataSource
import org.seasar.doma.jdbc.tx.LocalTransactionManager
import org.seasar.doma.jdbc.tx.TransactionManager
import javax.sql.DataSource

class AppConfig : Config {

    private val _dialect: Dialect

    private val _dataSource: LocalTransactionDataSource

    private val _transactionManager: TransactionManager

    constructor() {
        _dialect = H2Dialect()
        _dataSource = LocalTransactionDataSource(
                "jdbc:h2:mem:tutorial;DB_CLOSE_DELAY=-1", "sa", null);
        _transactionManager = LocalTransactionManager(
                _dataSource.getLocalTransaction(getJdbcLogger()));

    }

    override fun getDataSource(): DataSource {
        return _dataSource
    }

    override fun getDialect(): Dialect {
        return _dialect
    }

    override fun getTransactionManager(): TransactionManager {
        return  _transactionManager
    }

}