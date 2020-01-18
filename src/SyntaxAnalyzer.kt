class SyntaxAnalyzer(private val tokenStream: List<Token>) {

    enum class States {
        P, B, B1, T, S, S1, S2, S3, S4, X, C, F, H, A, K, L, Q, E, R, U, U1, U2, V, V1
    }

    private var stack: Stack = Stack()
    private var parseOrder = mutableListOf<Int>()

    fun parse() {
        var currentIndex = 0
        var currentToken: Token
        stack.push(States.P)
        while (tokenStream.isNotEmpty()) {
            currentToken = tokenStream[currentIndex]
            if (stack.peek() is Token) {
                if ((stack.peek() as Token).type == currentToken.type) {
                    stack.pop()
                    currentIndex++
                } else {
                    throw Exception("Syntax Error on token $currentIndex. Expected \"${(stack.peek() as Token).type}\"")
                }
            } else {
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
            }
        }
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
}