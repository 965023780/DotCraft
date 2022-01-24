package com.example.dotcraft.challenge.strategy

import com.example.dotcraft.Constants
import java.io.Serializable

class SimpleStrategy private constructor() : ChallengeStrategy, Serializable {
    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SimpleStrategy()
        }
    }

    override fun getRows(): Int = 3

    override fun getCols(): Int = 3

    override fun getWhiteDotNumbers(): Int = 4

    override fun getChallengeTag(): String = Constants.Opt.CHALLENGE_SIMPLE
}