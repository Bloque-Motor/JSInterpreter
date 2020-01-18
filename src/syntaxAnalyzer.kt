class syntaxAnalyzer {

    enum class States {
        P, B, B1, T, S, S1, S2, S3, S4, X, C, F, H, A, K, L, Q, E, R, U, U1, U2, V, V1
    }

    var state = States.P;
    private var stack: Stack = Stack()
    private var parse = mutableListOf<Int>()

    fun process(token: Token){
        when(state){
            States.P -> stateP(token)
            States.B-> stateB(token)
            States.B1-> stateB1(token)
            States.T-> stateT(token)
            States.S-> stateS(token)
            States.S1-> stateS1(token)
            States.S2-> stateS2(token)
            States.S3-> stateS3(token)
            States.S4-> stateS4(token)
            States.X-> stateX(token)
            States.C-> stateC(token)
            States.F-> stateF(token)
            States.H-> stateH(token)
            States.A-> stateA(token)
            States.K-> stateK(token)
            States.L-> stateL(token)
            States.Q-> stateQ(token)
            States.E-> stateE(token)
            States.R-> stateR(token)
            States.U-> stateU(token)
            States.U1-> stateU1(token)
            States.U2-> stateU2(token)
            States.V-> stateV(token)
            States.V1-> stateV1(token)
        }
    }

    private fun stateP(token: Token) {
        if (stack.peek() is Token) {
            if (stack.peek() == token)
                if (!stack.isEmpty()) stack.pop()
        }
        else {
            when (token.type){
                "var", "if", "while", "id", "return", "print", "input" -> {
                    stack.pop()
                    stack.push(States.B)
                    stack.push(States.P)
                    parse.add(1)
                }
                "function" -> {
                    stack.pop()
                    stack.push(States.F)
                    stack.push(States.P)
                    parse.add(2)
                }
                "Eof" -> {
                    stack.pop()
                    stack.push("Eof")
                    parse.add(3)
                }

            }
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