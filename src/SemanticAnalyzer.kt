import res.Stack
import res.Tuple
import res.Types

class SemanticAnalyzer(ssa: SyntaxSemanticAnalyzer) {

    private var aux : Stack = ssa.aux
    private var stack : Stack = ssa.stack
    private lateinit var auxType : Types

    fun SA1() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK && (aux.elementAt(1) as Tuple).second == Types.OK) {
            (aux.elementAt(2) as Tuple).second = Types.OK
        }
    }
    fun SA2() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK && (aux.elementAt(1) as Tuple).second == Types.OK) {
            (aux.elementAt(2) as Tuple).second = Types.OK
        }
    }
    fun SA3() {
        stack.pop()
        (aux.elementAt(1) as Tuple).second = Types.OK
    }
    fun SA4_1() {
        stack.pop()
        (stack.peek() as Tuple).second = (aux.peek() as Tuple).second
    }
    fun SA4_2() {
        stack.pop()
        if ((aux.elementAt(-1) as Tuple).second == Types.OK) (aux.elementAt(4) as Tuple).second = Types.OK
    }
    fun SA5() {
        stack.pop()
        if ((aux.elementAt(2) as Tuple).second == Types.BOOLEAN && (aux.peek() as Tuple).second == Types.OK) {
            (aux.elementAt(5) as Tuple).second = Types.OK
        }
    }
    fun SA6() {
        stack.pop()
        if ((aux.elementAt(4) as Tuple).second == Types.BOOLEAN && (aux.elementAt(7) as Tuple).second == Types.OK) {
            (aux.elementAt(5) as Tuple).second = Types.OK
        }
    }
    fun SA7() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK) (aux.elementAt(1) as Tuple).second = Types.OK
    }
    fun SA8() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK) (aux.elementAt(1) as Tuple).second = Types.OK
    }
    fun SA9() {
        stack.pop()
        if ((aux.elementAt(1) as Tuple).second == Types.OK) (aux.elementAt(3) as Tuple).second = Types.OK
    }
    fun SA10() {
        stack.pop()
        (aux.elementAt(1) as Tuple).second = Types.INT
    }
    fun SA11() {
        stack.pop()
        (aux.elementAt(1) as Tuple).second = Types.STRING
    }
    fun SA12() {
        stack.pop()
        (aux.elementAt(1) as Tuple).second = Types.BOOLEAN
    }
    fun SA13() {
        //TODO
    }
    fun SA14() {
        //TODO
    }
    fun SA15() {
        //TODO
    }
    fun SA16() {
        //TODO
    }
    fun SA17() {
        stack.pop()
        (aux.elementAt(3) as Tuple).second = (aux.elementAt(1) as Tuple).second
    }
    fun SA18() {
        stack.pop()
        if ((aux.elementAt(1) as Tuple).second == Types.INT) {
            (aux.elementAt(3) as Tuple).second = (aux.elementAt(1) as Tuple).second
        }
    }
    fun SA19() {
        stack.pop()
        (aux.elementAt(4) as Tuple).second = (aux.elementAt(2) as Tuple).second
    }
    fun SA20_1() {
        stack.pop()
        //TODO InsertTS(Aux[top], Aux[top-1])  Parametro1 id, Parametro2 tipo
        (stack.peek() as Tuple).second = (aux.elementAt(1) as Tuple).second
    }
    fun SA20_2() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK) (aux.elementAt(2) as Tuple).second = Types.OK
    }
    fun SA21() {
        //TODO
    }
    fun SA22_1() {
        stack.pop()
        (stack.peek() as Tuple).second = (aux.elementAt(1) as Tuple).second
    }
    fun SA22_2() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK) (aux.elementAt(1) as Tuple).second = Types.OK
    }
    fun SA24() {
        //TODO
    }
    fun SA26() {
        //TODO
    }
    fun SA27() {
        //TODO
    }
    fun SA28() {
        //TODO
    }
    fun SA30() {
        //TODO
    }
    fun SA31() {
        //TODO
    }
    fun SA33() {
        //TODO
    }
    fun SA35() {
        //TODO
    }
    fun SA37() {
        //TODO
    }
    fun SA39() {
        //TODO
    }
    fun SA41() {
        //TODO
    }
    fun SA42() {
        //TODO
    }
    fun SA43() {
        //TODO
    }
    fun SA45() {
        //TODO
    }
    fun SA46() {
        //TODO
    }
    fun SA47() {
        //TODO
    }
    fun SA48() {
        //TODO
    }
    fun SA49() {
        //TODO
    }
    fun SA50() {
        //TODO
    }
    fun SA51() {
        //TODO
    }
    fun SA53() {
        //TODO
    }
    fun SA54() {
        //TODO
    }
    fun SA55() {
        //TODO
    }
    fun SA56() {
        //TODO
    }
    fun SA57() {
        //TODO
    }
    fun SA58() {
        //TODO
    }
    fun SA59() {
        //TODO
    }

    fun popAux(num : Int) {
        for (i in 0..num) aux.pop()
    }

}