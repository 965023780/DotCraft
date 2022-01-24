package com.example.dotcraft.challenge.strategy

import com.example.dotcraft.Constants
import java.io.Serializable

class MediumStrategy private constructor(): ChallengeStrategy,Serializable {
    companion object{
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MediumStrategy()
        }
    }
    override fun getRows(): Int = 4

    override fun getCols(): Int = 4

    override fun getWhiteDotNumbers(): Int = 8

    override fun getChallengeTag(): String = Constants.Opt.CHALLENGE_MEDIUM
}