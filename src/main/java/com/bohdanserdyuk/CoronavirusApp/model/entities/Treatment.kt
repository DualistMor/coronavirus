package com.bohdanserdyuk.CoronavirusApp.model.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Treatment(
        @Id
        @GeneratedValue
        val id: Int = 0,
        val doctorId: Int = 0,
        val infectedId: Int = 0,
        @ManyToOne
        var vaccine: Vaccine? = null
)
