class SyntaxSemanticAnalyzer(private val tokenStream: List<Token>, val symbolTable: List<Identifier>) {

    enum class States {
        P, B, B1, T, S, S1, S2, S3, S4, X, C, F, H, A, K, L, Q, E, R, U, U1, U2, V, V1
    }

    enum class Types {
        OK, ERROR, BOOLEAN, VOID, INT, STRING
    }

    enum class SemanticAction {
        SA1, SA2, SA3, SA4, SA5, SA6, SA7, SA8, SA9, SA10, SA11, SA12, SA13, SA14, SA15, SA16, SA17, SA18, SA19, SA20, SA21, SA22, SA23, SA24, SA25, SA26, SA27, SA28, SA29, SA30, SA31, SA32, SA33, SA34, SA35, SA36, SA37, SA38, SA39, SA40, SA41, SA42, SA43, SA44, SA45, SA46, SA47, SA48, SA49, SA50, SA51, SA52, SA53, SA54, SA55, SA56, SA57, SA58, SA59, SA60
    }

    class Pair {
        lateinit var state: States
        lateinit var type: Types
    }

    private var stack: Stack = Stack()
    private var parseOrder = mutableListOf<Int>()
    private var aux: Stack = Stack()

    fun parse(): MutableList<Int> {
        var currentIndex = 0
        var currentToken: Token
        stack.push(States.P)
        while (currentIndex < tokenStream.size) {
            currentToken = tokenStream[currentIndex]
            when (stack.peek()) {

                is Token ->
                    if ((stack.peek() as Token).type == currentToken.type) {
                        stack.pop()
                        currentIndex++
                    } else {
                        throw Exception("Syntax Error on token $currentIndex. Expected \"${(stack.peek() as Token).type}\"")
                    }
                is States ->
                    when (stack.peek()) {
                        States.P -> stateP(currentToken)
                        States.B -> stateB(currentToken)
                        States.B1 -> stateB1(currentToken)
                        States.T -> stateT(currentToken)
                        States.S -> stateS(currentToken)
                        States.S1 -> stateS1(currentToken)
                        States.S2 -> stateS2(currentToken)
                        States.S3 -> stateS3(currentToken)
                        States.S4 -> stateS4(currentToken)
                        States.X -> stateX(currentToken)
                        States.C -> stateC(currentToken)
                        States.F -> stateF(currentToken)
                        States.H -> stateH(currentToken)
                        States.A -> stateA(currentToken)
                        States.K -> stateK(currentToken)
                        States.L -> stateL(currentToken)
                        States.Q -> stateQ(currentToken)
                        States.E -> stateE(currentToken)
                        States.R -> stateR(currentToken)
                        States.U -> stateU(currentToken)
                        States.U1 -> stateU1(currentToken)
                        States.U2 -> stateU2(currentToken)
                        States.V -> stateV(currentToken)
                        States.V1 -> stateV1(currentToken)
                    }
                is SemanticAction ->
                    when (stack.peek()) {
                        SemanticAction.SA1 -> SemanticAnalyzer.SA1()
                        SemanticAction.SA2 -> SemanticAnalyzer.SA2()
                        SemanticAction.SA3 -> SemanticAnalyzer.SA3()
                        SemanticAction.SA4 -> SemanticAnalyzer.SA4()
                        SemanticAction.SA5 -> SemanticAnalyzer.SA5()
                        SemanticAction.SA6 -> SemanticAnalyzer.SA6()
                        SemanticAction.SA7 -> SemanticAnalyzer.SA7()
                        SemanticAction.SA8 -> SemanticAnalyzer.SA8()
                        SemanticAction.SA9 -> SemanticAnalyzer.SA9()
                        SemanticAction.SA10 -> SemanticAnalyzer.SA10()
                        SemanticAction.SA11 -> SemanticAnalyzer.SA11()
                        SemanticAction.SA12 -> SemanticAnalyzer.SA12()
                        SemanticAction.SA13 -> SemanticAnalyzer.SA13()
                        SemanticAction.SA14 -> SemanticAnalyzer.SA14()
                        SemanticAction.SA15 -> SemanticAnalyzer.SA15()
                        SemanticAction.SA16 -> SemanticAnalyzer.SA16()
                        SemanticAction.SA17 -> SemanticAnalyzer.SA17()
                        SemanticAction.SA18 -> SemanticAnalyzer.SA18()
                        SemanticAction.SA19 -> SemanticAnalyzer.SA19()
                        SemanticAction.SA20 -> SemanticAnalyzer.SA20()
                        SemanticAction.SA21 -> SemanticAnalyzer.SA21()
                        SemanticAction.SA22 -> SemanticAnalyzer.SA22()
                        SemanticAction.SA23 -> SemanticAnalyzer.SA23()
                        SemanticAction.SA24 -> SemanticAnalyzer.SA24()
                        SemanticAction.SA25 -> SemanticAnalyzer.SA25()
                        SemanticAction.SA26 -> SemanticAnalyzer.SA26()
                        SemanticAction.SA27 -> SemanticAnalyzer.SA27()
                        SemanticAction.SA28 -> SemanticAnalyzer.SA28()
                        SemanticAction.SA29 -> SemanticAnalyzer.SA29()
                        SemanticAction.SA30 -> SemanticAnalyzer.SA30()
                        SemanticAction.SA31 -> SemanticAnalyzer.SA31()
                        SemanticAction.SA32 -> SemanticAnalyzer.SA32()
                        SemanticAction.SA33 -> SemanticAnalyzer.SA33()
                        SemanticAction.SA34 -> SemanticAnalyzer.SA34()
                        SemanticAction.SA35 -> SemanticAnalyzer.SA35()
                        SemanticAction.SA36 -> SemanticAnalyzer.SA36()
                        SemanticAction.SA37 -> SemanticAnalyzer.SA37()
                        SemanticAction.SA38 -> SemanticAnalyzer.SA38()
                        SemanticAction.SA39 -> SemanticAnalyzer.SA39()
                        SemanticAction.SA40 -> SemanticAnalyzer.SA40()
                        SemanticAction.SA41 -> SemanticAnalyzer.SA41()
                        SemanticAction.SA42 -> SemanticAnalyzer.SA42()
                        SemanticAction.SA43 -> SemanticAnalyzer.SA43()
                        SemanticAction.SA44 -> SemanticAnalyzer.SA44()
                        SemanticAction.SA45 -> SemanticAnalyzer.SA45()
                        SemanticAction.SA46 -> SemanticAnalyzer.SA46()
                        SemanticAction.SA47 -> SemanticAnalyzer.SA47()
                        SemanticAction.SA48 -> SemanticAnalyzer.SA48()
                        SemanticAction.SA49 -> SemanticAnalyzer.SA49()
                        SemanticAction.SA50 -> SemanticAnalyzer.SA50()
                        SemanticAction.SA51 -> SemanticAnalyzer.SA51()
                        SemanticAction.SA52 -> SemanticAnalyzer.SA52()
                        SemanticAction.SA53 -> SemanticAnalyzer.SA53()
                        SemanticAction.SA54 -> SemanticAnalyzer.SA54()
                        SemanticAction.SA55 -> SemanticAnalyzer.SA55()
                        SemanticAction.SA56 -> SemanticAnalyzer.SA56()
                        SemanticAction.SA57 -> SemanticAnalyzer.SA57()
                        SemanticAction.SA58 -> SemanticAnalyzer.SA58()
                        SemanticAction.SA59 -> SemanticAnalyzer.SA59()
                        SemanticAction.SA60 -> SemanticAnalyzer.SA60()
                    }
            }
        }

        return parseOrder
    }


    private fun stateP(token: Token) {
        when (token.type) {
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
                stack.push(Token("eof",""))
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
                stack.push(Token("var", ""))
                parseOrder.add(4)
            }
            "if" -> {
                stack.pop()
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
            else -> throw Exception("Syntax error. State B received ${token.type} token.")
        }
    }

    private fun stateB1(token: Token) {
        when (token.type) {
            "id", "return", "print", "input" -> {
                stack.pop()
                stack.push(States.S)
                parseOrder.add(8)
            }
            "{" -> {
                stack.pop()
                stack.push(Token("}", ""))
                stack.push(States.C)
                stack.push(Token("{", ""))
                parseOrder.add(9)
            }
            else -> throw Exception("Syntax error. State B1 received ${token.type} token.")
        }
    }

    private fun stateT(token: Token) {
        when (token.type) {
            "int" -> {
                stack.pop()
                stack.push(Token("int", ""))
                parseOrder.add(10)
            }
            "string" -> {
                stack.pop()
                stack.push(Token("string", ""))
                parseOrder.add(11)
            }
            "boolean" -> {
                stack.pop()
                stack.push(Token("boolean", ""))
                parseOrder.add(12)
            }
            else -> throw Exception("Syntax error. State T  received ${token.type} token.")
        }
    }

    private fun stateS(token: Token) {
        when (token.type) {
            "id" -> {
                stack.pop()
                stack.push(States.S1)
                stack.push(Token("id", ""))
                parseOrder.add(13)
            }
            "return" -> {
                stack.pop()
                stack.push(Token(";", ""))
                stack.push(States.X)
                stack.push(Token("return", ""))
                parseOrder.add(14)
            }
            "print" -> {
                stack.pop()
                stack.push(Token(";", ""))
                stack.push(Token(")", ""))
                stack.push(States.L)
                stack.push(Token("(", ""))
                stack.push(Token("print", ""))
                parseOrder.add(15)
            }
            "input" -> {
                stack.pop()
                stack.push(Token(";", ""))
                stack.push(Token(")", ""))
                stack.push(Token("id", ""))
                stack.push(Token("(", ""))
                stack.push(Token("input", ""))
                parseOrder.add(16)
            }
            else -> throw Exception("Syntax error. State S received ${token.type} token.")
        }
    }

    private fun stateS1(token: Token) {
        when (token.type) {
            "=" -> {
                stack.pop()
                stack.push(Token(";", ""))
                stack.push(States.E)
                stack.push(Token("=", ""))
                parseOrder.add(17)
            }
            "-=" -> {
                stack.pop()
                stack.push(Token(";", ""))
                stack.push(States.E)
                stack.push(Token("-=", ""))
                parseOrder.add(18)
            }
            "(" -> {
                stack.pop()
                stack.push(Token(";", ""))
                stack.push(Token(")", ""))
                stack.push(States.L)
                stack.push(Token("(", ""))
                parseOrder.add(19)
            }
            else -> throw Exception("Syntax error. State S1 received ${token.type} token.")
        }
    }

    private fun stateS2(token: Token) {
        when (token.type) {
            "id" -> {
                stack.pop()
                stack.push(States.S3)
                stack.push(Token("id", ""))
                parseOrder.add(20)
            }
            else -> throw Exception("Syntax error. State S2 received ${token.type} token.")
        }
    }

    private fun stateS3(token: Token) {
        when (token.type) {
            "=" -> {
                stack.pop()
                stack.push(States.S4)
                stack.push(States.E)
                stack.push(Token("=", ""))
                parseOrder.add(21)
            }
            "," -> {
                stack.pop()
                stack.push(States.S2)
                stack.push(Token(",", ""))
                parseOrder.add(22)
            }
            ";" -> {
                stack.pop()
                parseOrder.add(23)
            }
            else -> throw Exception("Syntax error. State S3 received ${token.type} token.")
        }
    }

    private fun stateS4(token: Token) {
        when (token.type) {
            "," -> {
                stack.pop()
                stack.push(States.S2)
                stack.push(Token(",", ""))
                parseOrder.add(24)
            }
            ";" -> {
                stack.pop()
                parseOrder.add(25)
            }
            else -> throw Exception("Syntax error. State S4 received ${token.type} token.")
        }
    }

    private fun stateX(token: Token) {
        when (token.type) {
            "!", "id", "number", "(", "cadena", "false", "true" -> {
                stack.pop()
                stack.push(States.E)
                parseOrder.add(26)
            }
            ";" -> {
                stack.pop()
                parseOrder.add(27)
            }
            else -> throw Exception("Syntax error. State X received ${token.type} token.")
        }
    }

    private fun stateC(token: Token) {
        when (token.type) {
            "var", "if", "while", "id", "return", "print", "input" -> {
                stack.pop()
                stack.push(States.C)
                stack.push(States.B)
                parseOrder.add(28)
            }
            "}" -> {
                stack.pop()
                parseOrder.add(29)
            }
            else -> throw Exception("Syntax error. State C received ${token.type} token.")
        }
    }

    private fun stateF(token: Token) {
        when (token.type) {
            "function" -> {
                stack.pop()
                stack.push(Token("}", ""))
                stack.push(States.C)
                stack.push(Token("{", ""))
                stack.push(Token(")", ""))
                stack.push(States.A)
                stack.push(Token("(", ""))
                stack.push(Token("id", ""))
                stack.push(States.H)
                stack.push(Token("function", ""))
                parseOrder.add(30)
            }
            else -> throw Exception("Syntax error. State F received ${token.type} token.")
        }
    }

    private fun stateH(token: Token) {
        when (token.type) {
            "int", "string", "boolean" -> {
                stack.pop()
                stack.push(States.T)
                parseOrder.add(31)
            }
            "id" -> {
                stack.pop()
                parseOrder.add(32)
            }
            else -> throw Exception("Syntax error. State H received ${token.type} token.")
        }
    }

    private fun stateA(token: Token) {
        when (token.type) {
            "int", "string", "boolean" -> {
                stack.pop()
                stack.push(States.K)
                stack.push(Token("id", ""))
                stack.push(States.T)
                parseOrder.add(33)
            }
            ")" -> {
                stack.pop()
                parseOrder.add(34)
            }
            else -> throw Exception("Syntax error. State A received ${token.type} token.")
        }
    }

    private fun stateK(token: Token) {
        when (token.type) {
            "," -> {
                stack.pop()
                stack.push(States.K)
                stack.push(Token("id", ""))
                stack.push(States.T)
                stack.push(Token(",", ""))
                parseOrder.add(35)
            }
            ")" -> {
                stack.pop()
                parseOrder.add(36)
            }
            else -> throw Exception("Syntax error. State K received ${token.type} token.")

        }
    }

    private fun stateL(token: Token) {
        when (token.type) {
            "!", "id", "number", "(", "cadena", "false", "true" -> {
                stack.pop()
                stack.push(States.Q)
                stack.push(States.E)
                parseOrder.add(37)
            }
            ")" -> {
                stack.pop()
                parseOrder.add(38)
            }
            else -> throw Exception("Syntax error. State L received ${token.type} token.")
        }
    }

    private fun stateQ(token: Token) {
        when (token.type) {
            "," -> {
                stack.pop()
                stack.push(States.Q)
                stack.push(States.E)
                stack.push(Token(",",""))
                parseOrder.add(39)
            }
            ")" -> {
                stack.pop()
                parseOrder.add(40)
            }
            else -> throw Exception("Syntax error. State Q received ${token.type} token.")
        }
    }

    private fun stateE(token: Token) {
        when (token.type){
            "!" -> {
                stack.pop()
                stack.push(States.E)
                stack.push(Token("!",""))
                parseOrder.add(41)
            }
            "id", "number", "(", "cadena", "false", "true" -> {
                stack.pop()
                stack.push(States.R)
                stack.push(States.U)
                parseOrder.add(42)
            }
            else -> throw Exception("Syntax error. State E received ${token.type} token.")
        }
    }

    private fun stateR(token: Token) {
        when (token.type){
            "<" -> {
                stack.pop()
                stack.push(States.U)
                stack.push((Token("<","")))
                parseOrder.add(43)
            }
            ")", ",", ";" -> {
                stack.pop()
                parseOrder.add((44))
            }
            else -> throw Exception("Syntax error. State R received ${token.type} token.")
        }
    }

    private fun stateU(token: Token) {
        when (token.type) {
            "id", "number", "(", "cadena", "false", "true" -> {
                stack.pop()
                stack.push(States.U2)
                stack.push(States.V)
                parseOrder.add(45)
            }
            else -> throw Exception("Syntax error. State U received ${token.type} token.")
        }
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
            else -> throw Exception("Syntax error. State U1 received ${token.type} token.")
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
            else -> throw Exception("Syntax error. State U2 received ${token.type} token.")
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
            "+","-","*","/","%","<",",",";",")" -> {
                stack.pop()
                parseOrder.add(60)
            }
            else -> throw Exception("Syntax error. State V1 received ${token.type} token.")
        }
    }

    private class Stack {
        val elements: MutableList<Any> = mutableListOf()

        fun isEmpty() = elements.isEmpty()
        fun size() = elements.size
        fun push(item: Any) = elements.add(item)
        fun pop(): Any? {
            val item = elements.lastOrNull()
            if (!isEmpty()) elements.removeAt(elements.size - 1)
            return item
        }

        fun peek(): Any? = elements.lastOrNull()
    }

    private fun checkAlgebra(tokens: MutableList<Token>) {

    }
}