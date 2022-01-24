package com.example.dotcraft

interface Constants {
    interface Sp {
        companion object {
            const val SHARED_PREFERENCE_FILE_NAME = "DotCraft_sp"
            const val LEVEL_PASSED = "level_passed"
            const val DEFAULT_LONG = -1L
            const val DEFAULT_INT = -1
            const val DEFAULT_BOOLEAN = false
            const val DEFAULT_STRING = "default"
        }
    }

    interface Opt {
        companion object {
            const val OPT_STATUS = "opt_status"
            const val OPT_STATUS_MAIN = "status_main"
            const val OPT_STATUS_CHALLENGE = "status_challenge"
            const val OPT_STATUS_LEVEL = "status_level"
            const val LEVEL = "level"
            const val CHALLENGE = "challenge"
            const val CHALLENGE_SIMPLE = "simple"
            const val CHALLENGE_MEDIUM = "medium"
            const val CHALLENGE_DIFFICULT = "difficult"
        }
    }

    interface Continue {
        companion object {
            const val INF_TYPE = "continue_type"
            const val INF_ROW = "continue_row"
            const val INF_COL = "continue_col"
            const val INF_DOT = "continue_dot"
            const val INF_CUR_DOT = "continue_cur_dot"
            const val INF_TIME = "continue_time"
        }
    }

    interface Rank {
        companion object {
            const val INF_NUMBER = "rank_number"
            const val INF_TYPE = "rank_type"
            const val INF_TIME = "rank_time"
        }

    }
}