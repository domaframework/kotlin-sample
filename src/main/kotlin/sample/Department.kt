package sample

import org.seasar.doma.Entity
import org.seasar.doma.Id
import org.seasar.doma.Metamodel
import org.seasar.doma.Version

@Entity(immutable = true, metamodel = Metamodel())
class Department(@Id val id: Int, val name: String, @Version val version: Int)