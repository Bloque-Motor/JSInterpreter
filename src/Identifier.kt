class Identifier (val id: Int,val lex: String){
    enum class Type {FUNCTION, INT, BOOLEAN, STRING, VOID}
    var type: Type? = null
        get() = field
        set(value) {
            field = value
        }
    var returnType: Type? = null
        get() = field
        set(value) {
            field = value
        }
    var parameterCount = 0
    var parameterList = mutableListOf<Type>()

    fun addParameter(type: Type){
        parameterList.add(type)
        parameterCount++
    }

}