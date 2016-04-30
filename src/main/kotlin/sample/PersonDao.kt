package sample

import org.seasar.doma.*
import org.seasar.doma.jdbc.Result

@Dao
interface PersonDao {

    @Script
    fun create()

    @Script
    fun drop()

    @Select
    fun selectById(@ParameterName("id") id:Int): Person

    @Insert
    fun insert(person: Person): Result<Person>

    @Update
    fun update(person: Person): Result<Person>

    @Delete
    fun delete(person: Person): Result<Person>
}