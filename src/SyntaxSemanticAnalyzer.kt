import res.Stack
import res.Tuple
import res.Types

class SyntaxSemanticAnalyzer(private val tokenStream: List<Token>, val symbolTable: List<Identifier>) {

    enum class States {
        P, B, B1, T, S, S1, S2, S3, S4, X, C, F, H, A, K, L, Q, E, R, U, U1, U2, V, V1
    }

    enum class SemanticAction {
        SA1, SA2, SA3, SA4_1, SA4_2, SA5, SA6, SA7, SA8, SA9, SA10, SA11, SA12, SA13, SA14, SA15, SA16, SA17, SA18, SA19, SA20_1, SA20_2, SA21, SA22_1, SA22_2, SA23, SA24, SA25, SA26, SA27, SA28, SA29, SA30, SA31, SA32, SA33, SA34, SA35, SA36, SA37, SA38, SA39, SA40, SA41, SA42, SA43, SA44, SA45, SA46, SA47, SA48, SA49, SA50, SA51, SA52, SA53, SA54, SA55, SA56, SA57, SA58, SA59, SA60, PA
    }

    var stack: Stack = Stack()
    private var parseOrder = mutableListOf<Int>()
    var aux: Stack = Stack()
    private var sa = SemanticAnalyzer(this)

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
                is Tuple ->
                    when ((stack.peek() as Tuple).first) {
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
                        SemanticAction.PA -> sa.popAux((stack.peek() as Tuple).second as Int)
                    }
                is SemanticAction ->
                    when (stack.peek()) {
                        SemanticAction.SA1 -> sa.SA1()
                        SemanticAction.SA2 -> sa.SA2()
                        SemanticAction.SA3 -> sa.SA3()
                        SemanticAction.SA4_1 -> sa.SA4_1()
                        SemanticAction.SA4_2 -> sa.SA4_2()
                        SemanticAction.SA5 -> sa.SA5()
                        SemanticAction.SA6 -> sa.SA6()
                        SemanticAction.SA7 -> sa.SA7()
                        SemanticAction.SA8 -> sa.SA8()
                        SemanticAction.SA9 -> sa.SA9()
                        SemanticAction.SA10 -> sa.SA10()
                        SemanticAction.SA11 -> sa.SA11()
                        SemanticAction.SA12 -> sa.SA12()
                        SemanticAction.SA13 -> sa.SA13()
                        SemanticAction.SA14 -> sa.SA14()
                        SemanticAction.SA15 -> sa.SA15()
                        SemanticAction.SA16 -> sa.SA16()
                        SemanticAction.SA17 -> sa.SA17()
                        SemanticAction.SA18 -> sa.SA18()
                        SemanticAction.SA19 -> sa.SA19()
                        SemanticAction.SA20_1 -> sa.SA20_1()
                        SemanticAction.SA20_2 -> sa.SA20_2()
                        SemanticAction.SA21 -> sa.SA21()
                        SemanticAction.SA22_1 -> sa.SA22_1()
                        SemanticAction.SA22_2 -> sa.SA22_2()
                        SemanticAction.SA24 -> sa.SA24()
                        SemanticAction.SA26 -> sa.SA26()
                        SemanticAction.SA27 -> sa.SA27()
                        SemanticAction.SA28 -> sa.SA28()
                        SemanticAction.SA30 -> sa.SA30()
                        SemanticAction.SA31 -> sa.SA31()
                        SemanticAction.SA33 -> sa.SA33()
                        SemanticAction.SA35 -> sa.SA35()
                        SemanticAction.SA37 -> sa.SA37()
                        SemanticAction.SA39 -> sa.SA39()
                        SemanticAction.SA41 -> sa.SA41()
                        SemanticAction.SA42 -> sa.SA42()
                        SemanticAction.SA43 -> sa.SA43()
                        SemanticAction.SA45 -> sa.SA45()
                        SemanticAction.SA46 -> sa.SA46()
                        SemanticAction.SA47 -> sa.SA47()
                        SemanticAction.SA48 -> sa.SA48()
                        SemanticAction.SA49 -> sa.SA49()
                        SemanticAction.SA50 -> sa.SA50()
                        SemanticAction.SA51 -> sa.SA51()
                        SemanticAction.SA53 -> sa.SA53()
                        SemanticAction.SA54 -> sa.SA54()
                        SemanticAction.SA55 -> sa.SA55()
                        SemanticAction.SA56 -> sa.SA56()
                        SemanticAction.SA57 -> sa.SA57()
                        SemanticAction.SA58 -> sa.SA58()
                        SemanticAction.SA59 -> sa.SA59()
                    }
            }
        }

        return parseOrder
    }


    private fun stateP(token: Token) {
        when (token.type) {
            "var", "if", "while", "id", "return", "print", "input" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 2))
                stack.push(SemanticAction.SA1)
                stack.push(Tuple(States.P, null))
                stack.push(Tuple(States.B, null))
                parseOrder.add(1)
            }
            "function" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 2))
                stack.push(SemanticAction.SA2)
                stack.push(Tuple(States.P, null))
                stack.push(Tuple(States.F, null))
                parseOrder.add(2)
            }
            "eof" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 1))
                stack.push(SemanticAction.SA3)
                stack.push(Token("eof",""))
                parseOrder.add(3)
            }
            else -> throw Exception("Syntax error. State P received ${token.type} token.")
        }
    }

    private fun stateB(token: Token) {
        when (token.type) {
            "var" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 4))
                stack.push(SemanticAction.SA4_2)
                stack.push(Token(";", ""))
                stack.push(Tuple(States.S2, null))
                stack.push(SemanticAction.SA4_1)
                stack.push(Tuple(States.T, null))
                stack.push(Token("var", ""))
                parseOrder.add(4)
            }
            "if" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 5))
                stack.push(SemanticAction.SA5)
                stack.push(Tuple(States.B1, null))
                stack.push(Token(")", ""))
                stack.push(Tuple(States.E, null))
                stack.push(Token("(", ""))
                stack.push(Token("if", ""))
                parseOrder.add(5)
            }
            "while" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 7))
                stack.push(SemanticAction.SA6)
                stack.push(Token("}", ""))
                stack.push(Tuple(States.C, null))
                stack.push(Token("{", ""))
                stack.push(Token(")", ""))
                stack.push(Tuple(States.E, null))
                stack.push(Token("(", ""))
                stack.push(Token("while", ""))
                parseOrder.add(6)
            }
            "id", "return", "print", "input" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 1))
                stack.push(SemanticAction.SA7)
                stack.push(Tuple(States.S, null))
                parseOrder.add(7)
            }
            else -> throw Exception("Syntax error. State B received ${token.type} token.")
        }
    }

    private fun stateB1(token: Token) {
        when (token.type) {
            "id", "return", "print", "input" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 1))
                stack.push(SemanticAction.SA8)
                stack.push(Tuple(States.S, null))
                parseOrder.add(8)
            }
            "{" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 3))
                stack.push(SemanticAction.SA9)
                stack.push(Token("}", ""))
                stack.push(Tuple(States.C, null))
                stack.push(Token("{", ""))
                parseOrder.add(9)
            }
            else -> throw Exception("Syntax error. State B1 received ${token.type} token.")
        }
    }

    private fun stateT(token: Token) {
        when (token.type) {
            "int" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 1))
                stack.push(SemanticAction.SA10)
                stack.push(Token("int", ""))
                parseOrder.add(10)
            }
            "string" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 1))
                stack.push(SemanticAction.SA11)
                stack.push(Token("string", ""))
                parseOrder.add(11)
            }
            "boolean" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 1))
                stack.push(SemanticAction.SA12)
                stack.push(Token("boolean", ""))
                parseOrder.add(12)
            }
            else -> throw Exception("Syntax error. State T  received ${token.type} token.")
        }
    }

    private fun stateS(token: Token) {
        when (token.type) {
            "id" -> {
                aux.push(stack.pop()!!)
                stack.push(States.S1)
                stack.push(Token("id", ""))
                parseOrder.add(13)
            }
            "return" -> {
                aux.push(stack.pop()!!)
                stack.push(Token(";", ""))
                stack.push(States.X)
                stack.push(Token("return", ""))
                parseOrder.add(14)
            }
            "print" -> {
                aux.push(stack.pop()!!)
                stack.push(Token(";", ""))
                stack.push(Token(")", ""))
                stack.push(States.L)
                stack.push(Token("(", ""))
                stack.push(Token("print", ""))
                parseOrder.add(15)
            }
            "input" -> {
                aux.push(stack.pop()!!)
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
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 3))
                stack.push(SemanticAction.SA17)
                stack.push(Token(";", ""))
                stack.push(Tuple(States.E, null))
                stack.push(Token("=", ""))
                parseOrder.add(17)
            }
            "-=" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 3))
                stack.push(SemanticAction.SA18)
                stack.push(Token(";", ""))
                stack.push(Tuple(States.E, null))
                stack.push(Token("-=", ""))
                parseOrder.add(18)
            }
            "(" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 4))
                stack.push(SemanticAction.SA19)
                stack.push(Token(";", ""))
                stack.push(Token(")", ""))
                stack.push(Tuple(States.L, null))
                stack.push(Token("(", ""))
                parseOrder.add(19)
            }
            else -> throw Exception("Syntax error. State S1 received ${token.type} token.")
        }
    }

    private fun stateS2(token: Token) {
        when (token.type) {
            "id" -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 2))
                stack.push(SemanticAction.SA20_2)
                stack.push(States.S3)
                stack.push(SemanticAction.SA20_1)
                stack.push(Token("id", ""))
                parseOrder.add(20)
            }
            else -> throw Exception("Syntax error. State S2 received ${token.type} token.")
        }
    }

    private fun stateS3(token: Token) {
        when (token.type) {
            "=" -> {
                aux.push(stack.pop()!!)
                stack.push(States.S4)
                stack.push(States.E)
                stack.push(Token("=", ""))
                parseOrder.add(21)
            }
            "," -> {
                aux.push(stack.pop()!!)
                stack.push(Tuple(SemanticAction.PA, 2))
                stack.push(SemanticAction.SA22_2)
                stack.push(States.S2)
                stack.push(SemanticAction.SA22_1)
                stack.push(Token(",", ""))
                parseOrder.add(22)
            }
            ";" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add(23)
            }
            else -> throw Exception("Syntax error. State S3 received ${token.type} token.")
        }
    }

    private fun stateS4(token: Token) {
        when (token.type) {
            "," -> {
                aux.push(stack.pop()!!)
                stack.push(States.S2)
                stack.push(Token(",", ""))
                parseOrder.add(24)
            }
            ";" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add(25)
            }
            else -> throw Exception("Syntax error. State S4 received ${token.type} token.")
        }
    }

    private fun stateX(token: Token) {
        when (token.type) {
            "!", "id", "number", "(", "cadena", "false", "true" -> {
                aux.push(stack.pop()!!)
                stack.push(States.E)
                parseOrder.add(26)
            }
            ";" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add(27)
            }
            else -> throw Exception("Syntax error. State X received ${token.type} token.")
        }
    }

    private fun stateC(token: Token) {
        when (token.type) {
            "var", "if", "while", "id", "return", "print", "input" -> {
                aux.push(stack.pop()!!)
                stack.push(States.C)
                stack.push(States.B)
                parseOrder.add(28)
            }
            "}" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add(29)
            }
            else -> throw Exception("Syntax error. State C received ${token.type} token.")
        }
    }

    private fun stateF(token: Token) {
        when (token.type) {
            "function" -> {
                aux.push(stack.pop()!!)
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
                aux.push(stack.pop()!!)
                stack.push(States.T)
                parseOrder.add(31)
            }
            "id" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add(32)
            }
            else -> throw Exception("Syntax error. State H received ${token.type} token.")
        }
    }

    private fun stateA(token: Token) {
        when (token.type) {
            "int", "string", "boolean" -> {
                aux.push(stack.pop()!!)
                stack.push(States.K)
                stack.push(Token("id", ""))
                stack.push(States.T)
                parseOrder.add(33)
            }
            ")" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add(34)
            }
            else -> throw Exception("Syntax error. State A received ${token.type} token.")
        }
    }

    private fun stateK(token: Token) {
        when (token.type) {
            "," -> {
                aux.push(stack.pop()!!)
                stack.push(States.K)
                stack.push(Token("id", ""))
                stack.push(States.T)
                stack.push(Token(",", ""))
                parseOrder.add(35)
            }
            ")" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add(36)
            }
            else -> throw Exception("Syntax error. State K received ${token.type} token.")

        }
    }

    private fun stateL(token: Token) {
        when (token.type) {
            "!", "id", "number", "(", "cadena", "false", "true" -> {
                aux.push(stack.pop()!!)
                stack.push(States.Q)
                stack.push(States.E)
                parseOrder.add(37)
            }
            ")" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add(38)
            }
            else -> throw Exception("Syntax error. State L received ${token.type} token.")
        }
    }

    private fun stateQ(token: Token) {
        when (token.type) {
            "," -> {
                aux.push(stack.pop()!!)
                stack.push(States.Q)
                stack.push(States.E)
                stack.push(Token(",",""))
                parseOrder.add(39)
            }
            ")" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add(40)
            }
            else -> throw Exception("Syntax error. State Q received ${token.type} token.")
        }
    }

    private fun stateE(token: Token) {
        when (token.type){
            "!" -> {
                aux.push(stack.pop()!!)
                stack.push(States.E)
                stack.push(Token("!",""))
                parseOrder.add(41)
            }
            "id", "number", "(", "cadena", "false", "true" -> {
                aux.push(stack.pop()!!)
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
                aux.push(stack.pop()!!)
                stack.push(States.U)
                stack.push((Token("<","")))
                parseOrder.add(43)
            }
            ")", ",", ";" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add((44))
            }
            else -> throw Exception("Syntax error. State R received ${token.type} token.")
        }
    }

    private fun stateU(token: Token) {
        when (token.type) {
            "id", "number", "(", "cadena", "false", "true" -> {
                aux.push(stack.pop()!!)
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
                aux.push(stack.pop()!!)
                stack.push(States.V)
                stack.push(Token("+", ""))
                parseOrder.add(46)
            }
            "-" -> {
                aux.push(stack.pop()!!)
                stack.push(States.V)
                stack.push(Token("-", ""))
                parseOrder.add(47)
            }
            "*" -> {
                aux.push(stack.pop()!!)
                stack.push(States.V)
                stack.push(Token("*", ""))
                parseOrder.add(48)
            }
            "/" -> {
                aux.push(stack.pop()!!)
                stack.push(States.V)
                stack.push(Token("/", ""))
                parseOrder.add(49)
            }
            "%" -> {
                aux.push(stack.pop()!!)
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
                aux.push(stack.pop()!!)
                stack.push(States.U2)
                stack.push(States.U1)
                parseOrder.add(51)
            }
            "<",")",",",";" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add(52)
            }
            else -> throw Exception("Syntax error. State U2 received ${token.type} token.")
        }

    }

    private fun stateV(token: Token) {
        when (token.type){
            "id" -> {
                aux.push(stack.pop()!!)
                stack.push(States.V1)
                stack.push(Token("id", ""))
                parseOrder.add(53)
            }
            "number"->{
                aux.push(stack.pop()!!)
                stack.push(Token("number", ""))
                parseOrder.add(54)
            }
            "(" ->{
                aux.push(stack.pop()!!)
                stack.push(Token("(", ""))
                stack.push(States.E)
                stack.push(Token(")", ""))
                parseOrder.add(55)
            }
            "cadena" -> {
                aux.push(stack.pop()!!)
                stack.push(Token("cadena", ""))
                parseOrder.add(56)
            }
            "false" -> {
                aux.push(stack.pop()!!)
                stack.push(Token("false", ""))
                parseOrder.add(57)
            }
            "true" -> {
                aux.push(stack.pop()!!)
                stack.push(Token("true", ""))
                parseOrder.add(58)
            }
            else -> throw Exception("Syntax error. State V received ${token.type} token.")
        }
    }

    private fun stateV1(token: Token) {
        when (token.type) {
            "(" -> {
                aux.push(stack.pop()!!)
                stack.push(Token(")", ""))
                stack.push(States.L)
                stack.push(Token("(",""))
                parseOrder.add(59)
            }
            "+","-","*","/","%","<",",",";",")" -> {
                var tuple = stack.pop()!! as Tuple
                tuple.second = Types.OK
                aux.push(tuple)
                parseOrder.add(60)
            }
            else -> throw Exception("Syntax error. State V1 received ${token.type} token.")
        }
    }
}