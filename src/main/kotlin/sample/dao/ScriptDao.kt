package sample.dao

import org.seasar.doma.Dao
import org.seasar.doma.Script
import org.seasar.doma.Sql

@Dao
interface ScriptDao {
    @Script
    fun create()

    @Sql("drop all objects;")
    @Script
    fun drop()
}
