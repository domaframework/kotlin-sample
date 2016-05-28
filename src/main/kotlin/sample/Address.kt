package sample

import org.seasar.doma.Embeddable

@Embeddable
data class Address(val city: String, val street: String)