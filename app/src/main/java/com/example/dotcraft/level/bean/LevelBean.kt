package com.example.dotcraft.level.bean

import com.example.dotcraft.level.strategy.LevelStrategy
import java.io.Serializable

class LevelBean(val strategy: LevelStrategy): Serializable {
    var passed = false
}