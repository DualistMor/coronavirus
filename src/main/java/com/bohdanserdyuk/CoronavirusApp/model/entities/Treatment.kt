package com.bohdanserdyuk.CoronavirusApp.model.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Treatment(
    @Id
    @GeneratedValue
    val id: Int,
    val doctorId: Int,
    val infectedId: Int,
    @ManyToOne
    var vaccine: Vaccine?
)
