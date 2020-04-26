package sample

import org.seasar.doma.*

@Entity(immutable = true)
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,
        val name: Name,
        val age: Int?,
        val address: Address,
        val departmentId: Int,
        val gender: Gender,
        @Version
        val version: Int = -1) {
        @Transient
        var department: Department? = null
}
