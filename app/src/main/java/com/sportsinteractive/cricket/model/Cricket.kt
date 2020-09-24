package com.sportsinteractive.cricket.model

import com.google.gson.JsonObject

data class Cricket(val Teams: JsonObject)

enum class STATUS {
    LOADING, SUCCESS, ERROR
}



