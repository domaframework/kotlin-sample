package sample

import org.seasar.doma.Entity
import org.seasar.doma.GeneratedValue
import org.seasar.doma.Id
import org.seasar.doma.ParameterName

@Entity(immutable = true)
data class Person(
        @Id
        @GeneratedValue(strategy = org.seasar.doma.GenerationType.IDENTITY)
        @ParameterName("id") val id:Int? = null,
        @ParameterName("name") val name:Name,
        @ParameterName("age") val age:Int?)