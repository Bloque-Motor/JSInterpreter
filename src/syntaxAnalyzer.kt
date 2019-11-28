class syntaxAnalyzer {

    enum class States {
        Z, A, B, D, E, C, F, G, H, J, K, L, M, N, O, Q, R, S, T, U, W, X, P
    }

    var state = States.Z;

    fun process(token: Token){
        when(state){
            States.Z -> stateZ(token)
            States.A-> stateA(token)
            States.B-> stateB(token)
            States.D-> stateD(token)
            States.E-> stateE(token)
            States.C-> stateC(token)
            States.F-> stateF(token)
            States.G-> stateG(token)
            States.H-> stateH(token)
            States.J-> stateJ(token)
            States.K-> stateK(token)
            States.L-> stateL(token)
            States.M-> stateM(token)
            States.N-> stateN(token)
            States.O-> stateO(token)
            States.Q-> stateQ(token)
            States.R-> stateR(token)
            States.S-> stateS(token)
            States.T-> stateT(token)
            States.U-> stateU(token)
            States.W-> stateW(token)
            States.X-> stateX(token)
            States.P-> stateP(token)
        }
    }

    private fun stateP(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateX(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateW(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateU(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateT(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateS(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateR(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateQ(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateO(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateN(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateM(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateL(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateK(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateJ(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateH(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateG(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateF(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateC(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateE(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateD(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateB(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateA(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun stateZ(token: Token) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}