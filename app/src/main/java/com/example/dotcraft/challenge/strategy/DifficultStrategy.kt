package com.example.dotcraft.challenge.strategy

import com.example.dotcraft.Constants
import java.io.Serializable

class DifficultStrategy private constructor() : ChallengeStrategy, Serializable {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DifficultStrategy()
        }
    }

    override fun getRows(): Int = 5

    override fun getCols(): Int = 5

    override fun getWhiteDotNumbers(): Int = 12

    override fun getChallengeTag(): String = Constants.Opt.CHALLENGE_DIFFICULT
}