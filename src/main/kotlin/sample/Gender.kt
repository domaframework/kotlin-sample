package sample

import org.seasar.doma.Domain

@Domain(valueType = String::class, accessorMethod = "getCode", factoryMethod = "of")
// if you use enum without additional value and also do not use its value for database, you can do like this.
// @Domain(valueType = String::class, accessorMethod = "name", factoryMethod = "valueOf")
enum class Gender(val code: String) {
    MALE("0"),
    FEMALE("1");

    companion object {
        @JvmStatic // doma requires JvmStatic annotation for factory method
        fun of(code: String): Gender =
            values().firstOrNull { it.code == code }
                ?: throw IllegalArgumentException("${Gender::class.simpleName} does not have such code => $code")
    }
}
