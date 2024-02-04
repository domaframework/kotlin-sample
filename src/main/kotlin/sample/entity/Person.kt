package sample.entity

import org.seasar.doma.Column
import org.seasar.doma.Entity
import org.seasar.doma.GeneratedValue
import org.seasar.doma.GenerationType
import org.seasar.doma.Id
import org.seasar.doma.Metamodel
import org.seasar.doma.Table
import org.seasar.doma.Version
import sample.domain.Gender
import sample.domain.Name

/**
 *
 */
@Entity(listener = PersonListener::class, metamodel = Metamodel())
@Table(name = "PERSON")
class Person : AbstractPerson() {
    /**  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Int = -1

    /**  */
    @Column(name = "NAME")
    var name: Name? = null

    /**  */
    @Column(name = "AGE")
    var age: Int? = -1

    /**  */
    @Column(name = "CITY")
    var city: String? = null

    /**  */
    @Column(name = "STREET")
    var street: String? = null

    /**  */
    @Column(name = "DEPARTMENT_ID")
    var departmentId: Int = -1

    /**  */
    @Column(name = "GENDER")
    var gender: Gender? = null

    /**  */
    @Version
    @Column(name = "VERSION")
    var version: Int = -1
}
