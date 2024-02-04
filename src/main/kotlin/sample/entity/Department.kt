package sample.entity

import org.seasar.doma.Column
import org.seasar.doma.Entity
import org.seasar.doma.GeneratedValue
import org.seasar.doma.GenerationType
import org.seasar.doma.Id
import org.seasar.doma.Metamodel
import org.seasar.doma.Table
import org.seasar.doma.Version
import sample.domain.Name

/**
 *
 */
@Entity(listener = DepartmentListener::class, metamodel = Metamodel())
@Table(name = "DEPARTMENT")
class Department : AbstractDepartment() {
    /**  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Int = -1

    /**  */
    @Column(name = "NAME")
    var name: Name? = null

    /**  */
    @Version
    @Column(name = "VERSION")
    var version: Int = -1
}
