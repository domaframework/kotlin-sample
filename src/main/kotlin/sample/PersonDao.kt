package sample

import org.seasar.doma.*
import org.seasar.doma.jdbc.Result

@Dao(config = AppConfig::class)
interface PersonDao {

    @Script
    fun create()

    @Script
    fun drop()

    @Select
    fun selectById(id:Int): Person

    @Select
    fun selectWithDeparmentById(id: Int): PersonDepartment

    @Insert
    fun insert(person: Person): Result<Person>

    @Update
    fun update(person: Person): Result<Person>

    @Delete
    fun delete(person: Person): Result<Person>
}