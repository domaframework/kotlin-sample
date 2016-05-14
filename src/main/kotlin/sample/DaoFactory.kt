package sample

object DaoFactory {
    fun <T> create(daoInterface: Class<T>): T {
        val implClassName = daoInterface.getName() + "Impl"
        try {
            val implClass = Class.forName(implClassName)
            return implClass.newInstance() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}