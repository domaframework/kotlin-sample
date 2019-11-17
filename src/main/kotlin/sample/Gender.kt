package sample

import org.seasar.doma.Domain

@Domain(valueType = String::class, accessorMethod = "getCode", factoryMethod = "of")
enum class Gender(val code:String) {
    MALE("0"),
    FEMALE("1");

    companion object {
        @JvmStatic
        fun of(code:String):Gender = values().firstOrNull { it.code == code } ?: throw IllegalArgumentException("hoge")
    }
}
