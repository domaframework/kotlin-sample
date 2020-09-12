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
class DepartmentListener : EntityListener<Department> {

    override fun preInsert(entity: Department, context: PreInsertContext<Department>) {
    }

    override fun preUpdate(entity: Department, context: PreUpdateContext<Department>) {
    }

    override fun preDelete(entity: Department, context: PreDeleteContext<Department>) {
    }

    override fun postInsert(entity: Department, context: PostInsertContext<Department>) {
    }

    override fun postUpdate(entity: Department, context: PostUpdateContext<Department>) {
    }

    override fun postDelete(entity: Department, context: PostDeleteContext<Department>) {
    }
}
