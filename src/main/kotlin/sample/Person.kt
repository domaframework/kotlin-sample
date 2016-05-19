package sample

import org.seasar.doma.Entity
import org.seasar.doma.GeneratedValue
import org.seasar.doma.Id

@Entity(immutable = true)
data class Person(
        @Id
        @GeneratedValue(strategy = org.seasar.doma.GenerationType.IDENTITY)
        val id:Int? = null,
        val name:Name,
        val age:Int?)
