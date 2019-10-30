class Automata(tp: TokenPrinter){

    var state = 0
    var token = ""
    var genToken = tp

    fun process(char: Char){
        when(state){
            0-> state0(char)
            1-> state1(char)
            2-> state2(char)
            3-> state3(char)
            4-> state4(char)
            5-> state5(char)
            6-> state6(char)
            8-> state8(char)
            9-> state9(char)
            11-> state11(char)
            12-> state12(char)
            13-> state13(char)
            15-> state15(char)
            16-> state16(char)
            17-> state17(char)
        }
    }

    private fun state0(char: Char){
        when{
            ((char == '+') || (char == '*') || (char == '%'))-> {
                state = 1
                state1(char)
            }
            (char == '<')-> {
                state = 2
                state2(char)
            }
            (char == '!')-> {
                state = 3
                state3(char)
            }
            (char == '=')-> {
                state = 4
                state4(char)
            }
            (char == '(')-> {
                state = 5
                state5(char)
            }
            (char == ')')-> {
                state = 6
                state6(char)
            }
            (char == '-')-> {
                state = 8
                state8(char)
            }
            (char in '0'..'9')-> {
                state = 9
                state9(char)
            }
            (char == '\'' )-> {
                state = 11
                state11(char)
            }
            ((char in 'a' .. 'z') || (char in 'A' .. 'Z')) -> {
                state = 13
                state13(char)
            }
            (char == '/')-> {
                state = 15
                state15(char)
            }
            else-> state = 0
        }
    }

    private fun state1(char: Char){
        genToken.addToken(8, char.toString())
        state = 0
    }

    private fun state2(char: Char){
        genToken.addToken(9, char.toString())
        state = 0
    }

    private fun state3(char: Char){
        genToken.addToken(10, char.toString())
        state = 0
    }

    private fun state4(char: Char){
        genToken.addToken(11, char.toString())
        state = 0
    }

    private fun state5(char: Char){
        genToken.addToken(15, char.toString())
        state = 0
    }

    private fun state6(char: Char){
        genToken.addToken(15, char.toString())
        state = 0
    }

    private fun state8(char: Char){
        if(char == '='){
            genToken.addToken(11, "-=")
        }else{
            genToken.addToken(8, "-")
        }
        state = 0
    }

    private fun state9(char: Char){
        when{
            (char in '0'..'9')-> {
                token += char
                state = 9
            }
            ((char in 'a'..'z') || (char in 'A'..'Z'))-> {
                token = ""
                state = 0
                throw Exception("Bad lexeme")
            }
            else-> {
                genToken.addToken(2, token)
                token = ""
                state = 0
                when{
                    (char == '+')-> {
                        state = 1
                        state1(char)
                    }
                    (char == '<')-> {
                        state = 2
                        state2(char)
                    }
                    (char == '!')-> {
                        state = 3
                        state3(char)
                    }
                    (char == '=')-> {
                        state = 4
                        state4(char)
                    }
                    (char == '(')-> {
                        state = 5
                        state5(char)
                    }
                    (char == ')')-> {
                        state = 6
                        state6(char)
                    }
                    else -> {
                        state = 0
                        state0(char)
                    }
                }
            }
        }
    }

    private fun state11(char: Char){
        state = 12
        token += char
    }

    private fun state12(char: Char){
        if(char != '\''){
            token += char
            state = 12
        }
        else{
            token += char
            if (token.length > 66) throw Exception("String too long")
            genToken.addToken(3, token)
            state = 0
            token = ""
        }
    }

    private fun state13(char: Char){
        if((char in 'a' .. 'z') || (char in 'A' .. 'Z') || (char == '_') || (char in '0' .. '9')){
            token += char
            state = 13
        }else{
            if(genToken.isKeyword(token)) {
                genToken.addToken(16, token) // identificadores/palabras reservadas
                state = 0
                token = ""
                when{
                    (char == '+')-> state1(char)
                    (char == '<')-> state2(char)
                    (char == '!')-> state3(char)
                    (char == '=')-> state4(char)
                    (char == '(')-> state5(char)
                    (char == ')')-> state6(char)
                }
            }else{
                genToken.addToken(12, token) // identificadores/palabras reservadas
                state = 0
                token = ""
                when{
                    (char == '+')-> state1(char)
                    (char == '<')-> state2(char)
                    (char == '!')-> state3(char)
                    (char == '=')-> state4(char)
                    (char == '(')-> state5(char)
                    (char == ')')-> state6(char)
                }
            }
        }
    }

    private fun state15(char: Char){
        state = 16
    }

    private fun state16(char: Char){
        when{
            (char == '/') -> {
                state = 17
                token = ""
            }
            (char in '0' .. '9') -> {
                genToken.addToken(8, "/")
                state = 9
                state9(char)
            }
            else -> {
                state = 0
                throw Exception("Bad lexeme")
            }
        }
    }

    private fun state17(char: Char){
        if(char == '\n'){
            state = 0
            genToken.addToken(1, token)
            token = ""
        }else {
            token += char
        }
    }
}
