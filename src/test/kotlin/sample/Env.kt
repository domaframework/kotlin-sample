package sample

import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import sample.dao.ScriptDao
import sample.dao.ScriptDaoImpl

internal class Env :
    BeforeTestExecutionCallback,
    AfterTestExecutionCallback,
    BeforeAllCallback,
    AfterAllCallback,
    ParameterResolver {
    private val config = DbConfig.create()
    private val scriptDao: ScriptDao = ScriptDaoImpl(config)

    override fun beforeTestExecution(context: ExtensionContext?) {
        config.transactionManager.required {
            scriptDao.create()
        }
    }

    override fun afterTestExecution(context: ExtensionContext?) {
        config.transactionManager.required {
            scriptDao.drop()
        }
    }

    override fun beforeAll(context: ExtensionContext?) {
        config.localTransaction.begin()
    }

    override fun afterAll(context: ExtensionContext?) {
        config.localTransaction.rollback()
    }

    override fun supportsParameter(
        parameterContext: ParameterContext?,
        extensionContext: ExtensionContext?,
    ): Boolean = parameterContext!!.parameter.type === DbConfig::class.java

    override fun resolveParameter(
        parameterContext: ParameterContext?,
        extensionContext: ExtensionContext?,
    ): Any? {
        return config
    }
}
