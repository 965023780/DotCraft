package com.example.dotcraft.challenge.strategy

class SimpleStrategy private constructor(): ChallengeStrategy{
    companion object{
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SimpleStrategy()
        }
    }

    override fun getRows(): Int = 3

    override fun getCols(): Int = 3

    override fun getWhiteDotNumbers(): Int = 4
}