package sample

import org.seasar.doma.Entity
import org.seasar.doma.GeneratedValue
import org.seasar.doma.GenerationType
import org.seasar.doma.Id
import org.seasar.doma.Metamodel
import org.seasar.doma.Transient
import org.seasar.doma.Version

@Entity(immutable = true, metamodel = Metamodel())
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
