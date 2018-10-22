class Automata(tp: TokenPrinter){

    var state = 0
    var token = ""
    var genToken = TokenPrinter()

    fun process(char: Char){
        when(state){
            0-> state0(char)
            1-> state1(char)
            2-> state2(char)
            3-> state3(char)
            4-> state4(char)
            5-> state5(char)
            6-> state6(char)
            7-> state7(char)
            8-> state8(char)
            9-> state9(char)
            10-> state10(char)
            11-> state11(char)
            12-> state12(char)
            13-> state13(char)
            14-> state14(char)
            15-> state15(char)
            16-> state16(char)
        }
    }

    fun state0(char: Char){

        when{
            (char == '+')-> state1(char)
            (char == '+')-> state1(char)
            (char == '<')-> state2(char)
            (char == '!')-> state3(char)
            (char == '=')-> state4(char)
            (char == '(')-> state5(char)
            (char == ')')-> state6(char)
            (char == '|')-> state7(char)
            (char in '0'..'9')-> state9(char)
            (char == '"' )-> state11(char)
            ((char in 'a' .. 'z') || (char in 'A' .. 'Z')) -> state13(char)
            (char == '/')-> state15(char)
        }


    }

    fun state1(char: Char){

        genToken.addToken("OP_arimetico", Character.toString(char))
        state = 0

    }

    fun state2(char: Char){

        genToken.addToken("OP_relacional", Character.toString(char))
        state = 0
    }

    fun state3(char: Char){

        genToken.addToken("OP_logico", Character.toString(char))
        state = 0

    }

    fun state4(char: Char){

        genToken.addToken("OP_asignacion", Character.toString(char))
        state = 0

    }

    fun state5(char: Char){

        genToken.addToken("OP_delimitador", Character.toString(char))
        state = 0

    }

    fun state6(char: Char){

        genToken.addToken("OP_delimitador", Character.toString(char))
        state = 0

    }

    fun state7(char: Char){

    }

    fun state8(char: Char){

    }

    fun state9(char: Char){

    }

    fun state10(char: Char){

    }

    fun state11(char: Char){

    }

    fun state12(char: Char){

    }

    fun state13(char: Char){

    }

    fun state14(char: Char){

    }

    fun state15(char: Char){

    }

    fun state16(char: Char){

    }

}

