package com.bohdanserdyuk.CoronavirusApp.model.entities

import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

data class Doctor(
        val id: Int,
        val name: String,
        val password: String,
        val cured: Int,
        val deaths: Int
) : Serializable