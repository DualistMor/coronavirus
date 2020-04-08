package com.bohdanserdyuk.CoronavirusApp.model.entities

import java.io.Serializable

data class Doctor(
        var id: Int,
        var name: String,
        var password: String,
        var cured: Int,
        var deaths: Int
) : Serializable