package com.example.dotcraft.rank.bean

class RankBean : Comparable<RankBean> {
    var id = 0
    var time = Long.MAX_VALUE
    var type = "level1"

    override fun compareTo(other: RankBean): Int = if (this.time > other.time) 1 else 0
}