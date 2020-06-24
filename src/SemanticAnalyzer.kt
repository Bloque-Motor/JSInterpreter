import res.Stack
import res.Tuple
import res.Types

class SemanticAnalyzer(ssa: SyntaxSemanticAnalyzer, private val symbolTable: MutableMap<String, List<Identifier>>) {

    private var aux : Stack = ssa.aux
    private var stack : Stack = ssa.stack
    private var currentFunction = "Global"

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
    fun SA13_1() {
        stack.pop()
        //TODO {if(!TS.containsType(Aux[top]) insertTSG(aux[top], int}
    }
    fun SA13_2() {
        stack.pop()
        //TODO  {IF (aux[top]==TS.getType(Aux[top-1]) Aux[top-2]=OK}
    }
    fun SA14() {
        stack.pop()
        //TODO  {IF(Aux[top-2] == TS.getReturnType(this)) Aux[top-5] = OK}
    }
    fun SA15() {
        stack.pop()
        if((aux.elementAt(2) as Tuple).second == Types.INT || (aux.elementAt(2) as Tuple).second == Types.STRING){
            (aux.elementAt(5) as Tuple).second = Types.OK
        }
    }
    fun SA16() {
        stack.pop()
        //TODO  {IF(!TS.containsType(Aux[top-2]) TS.insertType(Aux[top-2], int); IF(TS.getType(Aux[top-2] == (INT || String)) Aux[top-5] = OK}
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
        stack.pop()
        if((aux.peek() as Tuple).second == Types.OK && (aux.elementAt(1) as Tuple).second == (aux.elementAt(6) as Tuple).second){
            (aux.elementAt(3) as Tuple).second = Types.OK
        }
    }
    fun SA22_1() {
        stack.pop()
        (stack.peek() as Tuple).second = (aux.elementAt(1) as Tuple).second
    }
    fun SA22_2() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK) (aux.elementAt(1) as Tuple).second = Types.OK
    }
    fun SA24_1() {
        stack.pop()
        (aux.elementAt(2) as Tuple).second = (aux.peek() as Tuple).second
    }
    fun SA24_2() {
        stack.pop()
        (stack.peek() as Tuple).second = (aux.elementAt(2) as Tuple).second
    }
    fun SA26() {
        stack.pop()
        (aux.elementAt(1) as Tuple).second = (aux.peek() as Tuple).second
        }
    fun SA28() {
        stack.pop()
        if(((aux.peek() as Tuple).second == (aux.elementAt(1) as Tuple).second) && ((aux.peek() as Tuple).second == Types.OK)){
            (aux.elementAt(2) as Tuple).second = Types.OK
        }
    }
    fun SA30_1() {
        stack.pop()
        //TODO {IF (!TS.containsType(P[ntop]) TS.insert(P[ntop], function, Aux[top])}
        //symbolTable.get((stack.peek() as Token).value.toInt())
    }
    fun SA30_2() {
        stack.pop()
        if(((aux.elementAt(4) as Tuple).second == (aux.elementAt(1) as Tuple).second) && (aux.elementAt(1) as Tuple).second == Types.OK){
            (aux.elementAt(9) as Tuple).second = Types.OK
        }
    }
    fun SA31() {
        stack.pop()
        (aux.elementAt(1) as Tuple).second = (aux.peek() as Tuple).second
    }
    fun SA33_1() {
        stack.pop()
        //TODO  {IF(!TS.containsType(P[ntop]) Ts.insertType(P[ntop], Aux[top])}
    }
    fun SA33_2() {
        stack.pop()
        (aux.elementAt(3) as Tuple).second = (aux.peek() as Tuple).second
    }
    fun SA35_1() {
        stack.pop()
        //TODO  {IF(!TS.containsType(P[ntop]) Ts.insertType(P[ntop], Aux[top])}
    }
    fun SA35_2() {
        stack.pop()
        (aux.elementAt(4) as Tuple).second = (aux.peek() as Tuple).second
    }
    fun SA37() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK) {
            (aux.elementAt(2) as Tuple).second = (aux.elementAt(1) as Tuple).second
        }
        else if ((aux.peek() as Tuple).second == (aux.elementAt(1) as Tuple).second) {
            (aux.elementAt(2) as Tuple).second = (aux.peek() as Tuple).second
        }
    }
    fun SA39() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK || (aux.peek() as Tuple).second == (aux.elementAt(1) as Tuple).second) {
            (aux.elementAt(3) as Tuple).second = (aux.elementAt(1) as Tuple).second
        }
    }
    fun SA41() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.BOOLEAN) (aux.elementAt(2) as Tuple).second = Types.BOOLEAN
    }
    fun SA42() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK) {
            (aux.elementAt(2) as Tuple).second = (aux.elementAt(1) as Tuple).second
        }
        else if ((aux.peek() as Tuple).second == (aux.elementAt(1) as Tuple).second) {
            (aux.elementAt(2) as Tuple).second = Types.BOOLEAN
        }
    }
    fun SA43() {
        stack.pop()
        if((aux.peek() as Tuple).second == Types.INT) (aux.elementAt(2) as Tuple).second = Types.INT
    }
    fun SA45() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK) {
            (aux.elementAt(2) as Tuple).second = (aux.elementAt(1) as Tuple).second
        }
        else if ((aux.peek() as Tuple).second == (aux.elementAt(1) as Tuple).second) {
            (aux.elementAt(2) as Tuple).second = (aux.peek() as Tuple).second
        }
    }
    fun SA46() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.INT) (aux.elementAt(2) as Tuple).second = Types.INT
    }
    fun SA47() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.INT) (aux.elementAt(2) as Tuple).second = Types.INT
    }
    fun SA48() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.INT) (aux.elementAt(2) as Tuple).second = Types.INT
    }
    fun SA49() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.INT) (aux.elementAt(2) as Tuple).second = Types.INT
    }
    fun SA50() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.INT) (aux.elementAt(2) as Tuple).second = Types.INT
    }
    fun SA51() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK) {
            (aux.elementAt(2) as Tuple).second = (aux.elementAt(1) as Tuple).second
        }
        else if ((aux.peek() as Tuple).second == (aux.elementAt(1) as Tuple).second) {
            (aux.elementAt(2) as Tuple).second = (aux.peek() as Tuple).second
        }
    }
    fun SA53() {
        stack.pop()
        if ((aux.peek() as Tuple).second == Types.OK) {
            //TODO (aux.elementAt(2) as Tuple).second = TS.getType((aux.elementAt(1))
        }
        //TODO else if ((aux.peek() as Tuple).second == TS.getType((aux.elementAt(1) as Tuple).second)) {
        //    (aux.elementAt(2) as Tuple).second = (aux.peek() as Tuple).second
        //}
    }
    fun SA54() {
        stack.pop()
        (aux.elementAt(1) as Tuple).second = Types.INT
    }
    fun SA55() {
       stack.pop()
        (aux.elementAt(3) as Tuple).second = (aux.elementAt(1) as Tuple).second
    }
    fun SA56() {
        stack.pop()
        (aux.elementAt(1) as Tuple).second = Types.STRING
    }
    fun SA57() {
        stack.pop()
        (aux.elementAt(1) as Tuple).second = Types.BOOLEAN
    }
    fun SA58() {
        stack.pop()
        (aux.elementAt(1) as Tuple).second = Types.BOOLEAN
    }
    fun SA59() {
        stack.pop()
        (aux.elementAt(3) as Tuple).second = (aux.elementAt(1) as Tuple).second
    }

    fun popAux(num : Int) {
        for (i in 0..num) aux.pop()
    }

}