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

        genToken.addToken(8, Character.toString(char))
        state = 0

    }

    fun state2(char: Char){

        genToken.addToken(9, Character.toString(char))
        state = 0
    }

    fun state3(char: Char){

        genToken.addToken(10, Character.toString(char))
        state = 0

    }

    fun state4(char: Char){

        genToken.addToken(11, Character.toString(char))
        state = 0

    }

    fun state5(char: Char){

        genToken.addToken(15, Character.toString(char))
        state = 0

    }

    fun state6(char: Char){

        genToken.addToken(15, Character.toString(char))
        state = 0

    }

    fun state7(char: Char){



        var contador = 0;

        if(char == '|'){

            contador = 1
            state = 7
        }

        if(contador == 1){

            when{

                (char == '=')-> genToken.addToken(11, "|=")
                (char != '=')-> state = 0

            }

            contador = 0

        }

    }

    fun state8(char: Char){

        //no hace falta

    }

    fun state9(char: Char){

        var numero = char
        var numeroTotal = ""

        when{

            (char in '0'..'9')-> {
                numeroTotal = numeroTotal + numero
                state = 9
            }



            else-> {

                if(numeroTotal.toInt() < 32767) {
                    genToken.addToken("Entero", numeroTotal)
                }
                state = 0
            }



        }

    }

    fun state10(char: Char){

        //NO HACE FALTA

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

        var contador = 0;

        if(char == '/'){

            contador = 1
            state = 15
        }

        if(contador == 1){

            when{

                (char == '/')-> genToken.addToken(1, "//" )
                (char != '/')-> state = 0

            }

            contador = 0

        }

    }

    fun state16(char: Char){

       //no hace falta

    }

}

