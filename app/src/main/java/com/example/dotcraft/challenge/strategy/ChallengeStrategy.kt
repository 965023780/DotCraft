package com.example.dotcraft.challenge.strategy

interface ChallengeStrategy {
    fun getRows(): Int
    fun getCols(): Int
    fun getWhiteDotNumbers(): Int
}