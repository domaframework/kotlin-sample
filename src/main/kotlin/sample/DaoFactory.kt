package sample

import org.seasar.doma.jdbc.Config

object DaoFactory {
    fun <T> create(daoInterface: Class<T>, config: Config): T {
        val implClassName = daoInterface.getName() + "Impl"
        try {
            val implClass = Class.forName(implClassName)
            val constructor = implClass.getConstructor(Config::class.java)
            return constructor.newInstance(config) as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}