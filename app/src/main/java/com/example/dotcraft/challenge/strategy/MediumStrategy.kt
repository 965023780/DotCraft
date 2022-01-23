package com.example.dotcraft.challenge.strategy

class MediumStrategy private constructor(): ChallengeStrategy {
    companion object{
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MediumStrategy()
        }
    }
    override fun getRows(): Int = 4

    override fun getCols(): Int = 4

    override fun getWhiteDotNumbers(): Int = 8
}