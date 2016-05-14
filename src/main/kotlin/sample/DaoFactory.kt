package sample

import kotlin.reflect.KClass

object DaoFactory {
    @Suppress("UNCHECKED_CAST")
    fun <T> create(daoInterface: KClass<T>): T where T : Any {
        val implClassName = daoInterface.qualifiedName + "Impl"
        try {
            val implClass = Class.forName(implClassName)
            return implClass.newInstance() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}