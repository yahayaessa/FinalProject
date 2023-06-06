package com.yahayaessa.finalandroidproject.view.items

import com.yahayaessa.finalandroidproject.R

sealed class BottomBarItems(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Service : BottomBarItems(
        route = "Service",
        title = "Service",
        icon = R.drawable.app_icon
    )

    object Orders : BottomBarItems(
        route = "Orders",
        title = "Orders",
        icon = R.drawable.oreder_icon
    )

    object User : BottomBarItems(
        route = "User",
        title = "User",
        icon = R.drawable.user_icon
    )

    object More : BottomBarItems(
        route = "More",
        title = "More",
        icon = R.drawable.more_icon
    )


}