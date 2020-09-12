package sample.entity

import org.seasar.doma.jdbc.entity.EntityListener
import org.seasar.doma.jdbc.entity.PostDeleteContext
import org.seasar.doma.jdbc.entity.PostInsertContext
import org.seasar.doma.jdbc.entity.PostUpdateContext
import org.seasar.doma.jdbc.entity.PreDeleteContext
import org.seasar.doma.jdbc.entity.PreInsertContext
import org.seasar.doma.jdbc.entity.PreUpdateContext

/**
 *
 */
class PersonListener : EntityListener<Person> {

    override fun preInsert(entity: Person, context: PreInsertContext<Person>) {
    }

    override fun preUpdate(entity: Person, context: PreUpdateContext<Person>) {
    }

    override fun preDelete(entity: Person, context: PreDeleteContext<Person>) {
    }

    override fun postInsert(entity: Person, context: PostInsertContext<Person>) {
    }

    override fun postUpdate(entity: Person, context: PostUpdateContext<Person>) {
    }

    override fun postDelete(entity: Person, context: PostDeleteContext<Person>) {
    }
}
