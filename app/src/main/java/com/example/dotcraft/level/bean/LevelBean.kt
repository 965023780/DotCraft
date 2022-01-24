package com.example.dotcraft.level.bean

import com.example.dotcraft.level.strategy.LevelStrategy

class LevelBean(val strategy: LevelStrategy) {
    var passed = false
}