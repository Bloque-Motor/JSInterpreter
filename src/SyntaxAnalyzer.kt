import java.lang.Exception

class SyntaxAnalyzer (private val tokenStream: List<Token>) {

    enum class States {
        P, B, B1, T, S, S1, S2, S3, S4, X, C, F, H, A, K, L, Q, E, R, U, U1, U2, V, V1
    }

    private var stack: Stack = Stack()
    private var parseOrder = mutableListOf<Int>()

    fun parse(){
        var currentIndex = 0
        var currentToken: Token
        stack.push(States.P)
        while(tokenStream.isNotEmpty()){
            currentToken = tokenStream[currentIndex]
            if (stack.peek() is Token) {
                if ((stack.peek() as Token).type == currentToken.type) {
                    stack.pop()
                    currentIndex++
                }else{
                    throw Exception("Syntax Error on token $currentIndex. Expected \"${(stack.peek() as Token).type}\"")
                }
            }else{
                when(stack.peek()){
                    States.P -> stateP(currentToken)
                    States.B-> stateB(currentToken)
                    States.B1-> stateB1(currentToken)
                    States.T-> stateT(currentToken)
                    States.S-> stateS(currentToken)
                    States.S1-> stateS1(currentToken)
                    States.S2-> stateS2(currentToken)
                    States.S3-> stateS3(currentToken)
                    States.S4-> stateS4(currentToken)
                    States.X-> stateX(currentToken)
                    States.C-> stateC(currentToken)
                    States.F-> stateF(currentToken)
                    States.H-> stateH(currentToken)
                    States.A-> stateA(currentToken)
                    States.K-> stateK(currentToken)
                    States.L-> stateL(currentToken)
                    States.Q-> stateQ(currentToken)
                    States.E-> stateE(currentToken)
                    States.R-> stateR(currentToken)
                    States.U-> stateU(currentToken)
                    States.U1-> stateU1(currentToken)
                    States.U2-> stateU2(currentToken)
                    States.V-> stateV(currentToken)
                    States.V1-> stateV1(currentToken)
                }
            }
        }
    }


    private fun stateP(token: Token) {
            when (token.type){
                "var", "if", "while", "id", "return", "print", "input" -> {
                    stack.pop()
                    stack.push(States.P)
                    stack.push(States.B)
                    parseOrder.add(1)
                }
                "function" -> {
                    stack.pop()
                    stack.push(States.P)
                    stack.push(States.F)
                    parseOrder.add(2)
                }
                "eof" -> {
                    stack.pop()
                    stack.push("eof")
                    parseOrder.add(3)
                }
                else -> throw Exception("Syntax error. State P received ${token.type} token.")
            }
    }

    private fun stateB(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateB1(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateT(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateS(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateS1(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateS2(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateS3(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateS4(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateX(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateC(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateF(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateH(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateA(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateK(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateL(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateQ(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateE(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateR(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateU(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateU1(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateU2(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateV(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateV1(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private class Stack{
        val elements: MutableList<Any> = mutableListOf()

        fun isEmpty() = elements.isEmpty()
        fun size() = elements.size
        fun push(item: Any) = elements.add(item)
        fun pop() : Any? {
            val item = elements.lastOrNull()
            if (!isEmpty()) elements.removeAt(elements.size - 1)
            return item
        }
        fun peek() : Any? = elements.lastOrNull()
    }

}