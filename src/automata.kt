import java.lang.Exception
import javax.xml.stream.events.Comment

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
            7-> state7(char)
            8-> state8(char)
            9-> state9(char)
//            10-> state10(char)
            11-> state11(char)
            12-> state12(char)
            13-> state13(char)
//            14-> state14(char)
            15-> state15(char)
            16-> state16(char)
        }
    }

    fun state0(char: Char){

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
            (char == '|')-> {
                state = 7
                state7(char)
            }
            (char in '0'..'9')-> {
                state = 9
                state9(char)
            }
            (char == '"' )-> {
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

        genToken.addToken(8, Character.toString(char))
        state = 0

    }

    private fun state2(char: Char){

        when(char){
            '<' -> token = token + char
            '=' ->{
                token = token + char
                genToken.addToken(9, token)
                token = ""
                state = 0
            }
            else ->{
                genToken.addToken(9, token)
                token = ""
                state = 0
                state0(char)
            }
        }
    }

    private fun state3(char: Char){

        genToken.addToken(10, Character.toString(char))
        state = 0

    }

    private fun state4(char: Char){

        genToken.addToken(11, Character.toString(char))
        state = 0

    }

    private fun state5(char: Char){

        genToken.addToken(15, Character.toString(char))
        state = 0

    }

    private fun state6(char: Char){

        genToken.addToken(15, Character.toString(char))
        state = 0

    }

    private fun state7(char: Char){

        state = 8

    }

    private fun state8(char: Char){

        if(char == '='){

            genToken.addToken(11, "|=")
        }

        state = 0
    }

    private fun state9(char: Char){

        when{

            (char in '0'..'9')-> {
                token = token + char
                state = 9
            }

            ((char in 'a'..'z') || (char in 'A'..'Z'))-> {
                token = ""
                state = 0
                throw Exception("Bad lexeme")

            }

            else-> {

                if(token.toInt() < 32767) {
                    genToken.addToken(2, token)
                    token = ""
                    state = 0

                }
                else{
                    token = ""
                    state = 0
                    throw Exception("Bad lexeme")

                }

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

/*    private fun state10(char: Char){

        //NO HACE FALTA

    }*/

    private fun state11(char: Char){

        state = 12
        token = token + char

    }

    private fun state12(char: Char){

        if(char != '"'){

            token = token + char
            state = 12
        }

        else{

            token = token + char
            genToken.addToken(3, token)
            state = 0
            token = ""
        }
    }

    private fun state13(char: Char){

        if((char in 'a' .. 'z') || (char in 'A' .. 'Z') || (char == '_') || (char in '0' .. '9')){

            token = token + char
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

/*
    private fun state14(char: Char){


        //no  hace falta
    }
*/

    private fun state15(char: Char){

        state = 16

    }

    private fun state16(char: Char){

        if(char == '/'){

            genToken.addToken(1, "//")

        }

        state = 0
        throw Exception("comment")
    }
}
