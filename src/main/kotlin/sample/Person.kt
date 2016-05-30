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
        @Version
        val version: Int = -1)
