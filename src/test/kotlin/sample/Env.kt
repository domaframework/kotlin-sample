package sample

import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.seasar.doma.jdbc.Config

internal class Env :
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback,
        ParameterResolver {

    private val scriptDao: ScriptDao = ScriptDaoImpl(DbConfig)

    override fun beforeTestExecution(context: ExtensionContext?) {
        DbConfig.transactionManager.required {
            scriptDao.create()
        }
        DbConfig.localTransaction.begin()
    }

    override fun afterTestExecution(context: ExtensionContext?) {
        DbConfig.localTransaction.rollback()
        DbConfig.transactionManager.required {
            scriptDao.drop()
        }
    }

    override fun supportsParameter(
            parameterContext: ParameterContext?,
            extensionContext: ExtensionContext?
    ): Boolean =
            parameterContext!!.parameter.type === Config::class.java


    override fun resolveParameter(
            parameterContext: ParameterContext?,
            extensionContext: ExtensionContext?
    ): Any? {
        return DbConfig
    }
}
