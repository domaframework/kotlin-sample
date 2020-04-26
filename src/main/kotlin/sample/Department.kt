package sample

import org.seasar.doma.Entity
import org.seasar.doma.Id
import org.seasar.doma.Version

@Entity(immutable = true)
data class Department(
        @Id
        val id: Int?,
        val name: String,
        @Version
        val version: Int = -1
)