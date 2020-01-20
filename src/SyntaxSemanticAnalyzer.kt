class SyntaxSemanticAnalyzer(private val tokenStream: List<Token>, val symbolTable: List<Identifier>) {

    enum class States {
        P, B, B1, T, S, S1, S2, S3, S4, X, C, F, H, A, K, L, Q, E, R, U, U1, U2, V, V1
    }

    private var stack = Stack()
    private var parseOrder = mutableListOf<Int>()

    private var inVariableDeclaration = false
    private var inFunctionDeclaration = false
    private var inVariableAssignment = false
    private var inFunctionParametrization = false

    private var parsingFunctionId = -1
    private var curlyBraceLevel = 0

    private var leftOfAssignment = -1
    private var auxType :Identifier.Type? = null
    private var returnType :Identifier.Type? = null
    private var typeListAux = mutableListOf<Identifier.Type>()

    fun parse(): MutableList<Int> {
        var currentIndex = 0
        var currentToken: Token
        stack.push(States.P)
        while (currentIndex < tokenStream.size) {
            currentToken = tokenStream[currentIndex]
            if (stack.peek() is Token) {
                if ((stack.peek() as Token).type == currentToken.type) {
                    if(inFunctionDeclaration && currentToken.type == "id"){
                        parsingFunctionId = currentToken.value.toInt()
                        symbolTable[parsingFunctionId].type = Identifier.Type.FUNCTION
                        symbolTable[parsingFunctionId].returnType = returnType
                        inFunctionDeclaration = false
                        returnType = null
                    }
                    if (inFunctionParametrization && currentToken.type =="id"){
                        symbolTable[currentToken.value.toInt()].type = auxType
                        auxType = null
                    }
                    stack.pop()
                    currentIndex++
                    println(currentIndex)
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
                inVariableDeclaration = true
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

                curlyBraceLevel++
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

                if(inVariableDeclaration) auxType = Identifier.Type.INT
                if(inFunctionDeclaration) returnType = Identifier.Type.INT
                if(inFunctionParametrization) {
                    symbolTable[parsingFunctionId].addParameter(Identifier.Type.INT)
                    auxType = Identifier.Type.INT
                }
            }
            "string" -> {
                stack.pop()
                stack.push(Token("string", ""))
                parseOrder.add(11)

                if(inVariableDeclaration) auxType = Identifier.Type.STRING
                if(inFunctionDeclaration) returnType = Identifier.Type.STRING
                if(inFunctionParametrization) {
                    symbolTable[parsingFunctionId].addParameter(Identifier.Type.STRING)
                    auxType = Identifier.Type.STRING
                }

            }
            "boolean" -> {
                stack.pop()
                stack.push(Token("boolean", ""))
                parseOrder.add(12)

                if(inVariableDeclaration) auxType = Identifier.Type.BOOLEAN
                if(inFunctionDeclaration) returnType = Identifier.Type.BOOLEAN
                if(inFunctionParametrization) {
                    symbolTable[parsingFunctionId].addParameter(Identifier.Type.BOOLEAN)
                    auxType = Identifier.Type.BOOLEAN
                }
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

                inVariableAssignment = true
                if(symbolTable[token.value.toInt()].type == null){
                    throw Exception("Semantic error: ${symbolTable[token.value.toInt()].lex} is not defined.")
                }else{
                    typeListAux.add(symbolTable[token.value.toInt()].type!!)
                    leftOfAssignment = token.value.toInt()
                }
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

                inVariableAssignment = true
                if(symbolTable[token.value.toInt()].type == null){
                    symbolTable[token.value.toInt()].type = auxType
                    leftOfAssignment = token.value.toInt()
                }else{
                    throw Exception("Semantic error. Variable ${symbolTable[token.value.toInt()].lex} is already defined in the scope.")
                }
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
                inVariableAssignment = false
                leftOfAssignment = -1
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
                inVariableDeclaration = false
                auxType = null
                inVariableAssignment = false
                leftOfAssignment = -1
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

                inVariableAssignment = false
                leftOfAssignment = -1

                if(symbolTable[parsingFunctionId].returnType != Identifier.Type.VOID)
                    throw Exception("Semantic error: Type missmatch. Expected ${symbolTable[parsingFunctionId].returnType} as return " +
                            "type for ${symbolTable[parsingFunctionId].lex}. Got VOID.")
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
                curlyBraceLevel--
                if (parsingFunctionId != -1 && curlyBraceLevel == 0) parsingFunctionId = -1
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

                inFunctionDeclaration = true
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

                if(inFunctionDeclaration) returnType = Identifier.Type.VOID
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

                inFunctionParametrization = true
            }
            ")" -> {
                stack.pop()
                parseOrder.add(34)
                inFunctionParametrization = false
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
                inFunctionParametrization = false
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
            ")", ","->{
                stack.pop()
                parseOrder.add(44)
            }
            ";" -> {
                stack.pop()
                parseOrder.add(44)

                if(inVariableAssignment){
                    var leftType: Identifier.Type?
                    if(typeListAux.isNotEmpty()){
                        leftType = symbolTable[leftOfAssignment].type
                        for(type in typeListAux){
                            if (leftType != type) throw Exception("Semantic error: type mismatch. Expected $leftType found $type.")
                        }
                    }
                }

                inVariableAssignment = false
                leftOfAssignment = -1
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
            "<",")","," ->{
                stack.pop()
                parseOrder.add(52)
            }
            ";" -> {
                stack.pop()
                parseOrder.add(52)

                inVariableAssignment = false
                leftOfAssignment = -1
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

                if(symbolTable[token.value.toInt()].type == null){
                    throw Exception("Semantic error: ${symbolTable[token.value.toInt()].lex} is not defined.")
                }else{
                    typeListAux.add(symbolTable[token.value.toInt()].type!!)
                }
            }
            "number"->{
                stack.pop()
                stack.push(Token("number", ""))
                parseOrder.add(54)

                typeListAux.add(Identifier.Type.INT)
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

                typeListAux.add(Identifier.Type.STRING)
            }
            "false" -> {
                stack.pop()
                stack.push(Token("false", ""))
                parseOrder.add(57)

                typeListAux.add(Identifier.Type.BOOLEAN)
            }
            "true" -> {
                stack.pop()
                stack.push(Token("true", ""))
                parseOrder.add(58)

                typeListAux.add(Identifier.Type.BOOLEAN)
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
            "+","-","*","/","%","<",",",")" -> {
                stack.pop()
                parseOrder.add(60)
            }
            ";"->{
                stack.pop()
                parseOrder.add(60)

                inVariableAssignment = false
                leftOfAssignment = -1
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
}