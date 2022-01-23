package com.example.dotcraft

interface Constants {
    interface Sp {
        companion object {
            const val SHARED_PREFERENCE_FILE_NAME = "DotCraft_sp"
            const val LEVEL_PASSED = "level_passed"
        }
    }

    interface Opt {
        companion object {
            const val OPT_STATUS = "opt_status"
            const val OPT_STATUS_MAIN = "status_main"
            const val OPT_STATUS_CHALLENGE = "status_challenge"
            const val OPT_STATUS_LEVEL = "status_level"
        }
    }
}