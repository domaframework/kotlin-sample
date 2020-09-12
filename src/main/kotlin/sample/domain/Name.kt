package sample.domain

import org.seasar.doma.Domain

@Domain(valueType = String::class)
data class Name(val value: String)
