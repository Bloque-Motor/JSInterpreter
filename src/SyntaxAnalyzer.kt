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
        when (token.type) {
            "var" -> {
                stack.pop()
                stack.push(Token(";", ""))
                stack.push(States.S2)
                stack.push(States.T)
                stack.push(Token("var",""))
                parseOrder.add(4)
            }
            "if" -> {
                stack.push(States.B1)
                stack.push(Token(")", ""))
                stack.push(States.E)
                stack.push(Token("(", ""))
                stack.push(Token("if", ""))
                parseOrder.add(5)
            }
            "while" -> {
                stack.pop()
                stack.push(Token("}", ""))
                stack.push(States.C)
                stack.push(Token("{", ""))
                stack.push(Token(")", ""))
                stack.push(States.E)
                stack.push(Token("(", ""))
                stack.push(Token("while", ""))
                parseOrder.add(6)
            }
            "id", "return", "print", "input" -> {
                stack.pop()
                stack.push(States.S)
                parseOrder.add(7)
            }
        }
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
        when(token.type){
            "+" -> {
                stack.pop()
                stack.push(States.V)
                stack.push(Token("+", ""))
                parseOrder.add(46)
            }
            "-" -> {
                stack.pop()
                stack.push(States.V)
                stack.push(Token("-", ""))
                parseOrder.add(47)
            }
            "*" -> {
                stack.pop()
                stack.push(States.V)
                stack.push(Token("*", ""))
                parseOrder.add(48)
            }
            "/" -> {
                stack.pop()
                stack.push(States.V)
                stack.push(Token("/", ""))
                parseOrder.add(49)
            }
            "%" -> {
                stack.pop()
                stack.push(States.V)
                stack.push(Token("%", ""))
                parseOrder.add(50)
            }
        }
    }

    private fun stateU2(token: Token) {
        when(token.type){
            "+","-","*","/","%" -> {
                stack.pop()
                stack.push(States.U2)
                stack.push(States.U1)
                parseOrder.add(51)
            }
            "<",")",",",";" -> {
                stack.pop()
                parseOrder.add(52)
            }
        }

    }

    private fun stateV(token: Token) {
        when (token.type){
            "id" -> {
                stack.pop()
                stack.push(States.V1)
                stack.push(Token("id", ""))
                parseOrder.add(53)
            }
            "number"->{
                stack.pop()
                stack.push(Token("number", ""))
                parseOrder.add(54)
            }
            "(" ->{
                stack.pop()
                stack.push(Token("(", ""))
                stack.push(States.E)
                stack.push(Token(")", ""))
                parseOrder.add(55)
            }
            "cadena" -> {
                stack.pop()
                stack.push(Token("cadena", ""))
                parseOrder.add(56)
            }
            "false" -> {
                stack.pop()
                stack.push(Token("false", ""))
                parseOrder.add(57)
            }
            "true" -> {
                stack.pop()
                stack.push(Token("true", ""))
                parseOrder.add(58)
            }
            else -> throw Exception("Syntax error. State V received ${token.type} token.")
        }
    }

    private fun stateV1(token: Token) {
        when (token.type) {
            "(" -> {
                stack.pop()
                stack.push(Token(")", ""))
                stack.push(States.L)
                stack.push(Token("(",""))
                parseOrder.add(59)
            }
            "+","-","*","/","%","<",",",";" -> {
                stack.pop()
                parseOrder.add(60)
            }
            else -> throw Exception("Syntax error. State V1 received ${token.type} token.")
        }
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