package com.example.dotcraft.challenge.strategy

class DifficultStrategy private constructor() : ChallengeStrategy {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DifficultStrategy()
        }
    }

    override fun getRows(): Int = 5

    override fun getCols(): Int = 5

    override fun getWhiteDotNumbers(): Int = 12
}