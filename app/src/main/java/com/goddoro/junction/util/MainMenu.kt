package com.goddoro.junction.util

import androidx.annotation.IdRes
import com.goddoro.junction.R


/**
 * created By DORO 5/21/21
 */

enum class MainMenu(@IdRes override val menuId: Int, override val idx: Int) : IMainMenu {

    FEED(R.id.nav_item_video_feed, 0),
    EXPLORE(R.id.nav_item_explore, 1),

    ;

    companion object {
        fun parseIdToIdx(@IdRes id: Int) = values().indexOfFirst { it.menuId == id }
        fun parseIdToMainMenu(@IdRes id: Int) = values().first { it.menuId == id }
        fun parseIndexToMainMenu(idx: Int) = values().first { it.idx == idx }
    }
}