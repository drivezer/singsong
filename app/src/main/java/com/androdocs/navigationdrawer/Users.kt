package com.androdocs.navigationdrawer

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Users(
    var namesong: String? = "",
    var linkchord: String? = "",
    var textsong: String? = "",
    var linksong: String? = ""
)