package com.bohdanserdyuk.CoronavirusApp.model.entities

import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

data class Doctor(
        var id: Int,
        var name: String,
        var password: String,
        var cured: Int,
        var deaths: Int
) : Serializable