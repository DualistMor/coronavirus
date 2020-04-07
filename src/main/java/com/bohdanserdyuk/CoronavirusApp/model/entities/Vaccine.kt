package com.bohdanserdyuk.CoronavirusApp.model.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Vaccine(
        @Id
        @GeneratedValue
        var id: Int = 0,
        var name: String = "",
        var recoveryChance: Float = 0f
)
