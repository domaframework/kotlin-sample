package sample

import org.seasar.doma.*

@Entity(immutable = true)
data class PersonDepartment(
        val id: Int,
        val name: Name,
        val departmentId: Int,
        val departmentName: Name)
