package com.bohdanserdyuk.CoronavirusApp.model.entities

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Infected(
        @Id
        @GeneratedValue
        val id: Int = 0,
        val name: String = "",
        val address: String = "",
        val age: Int= 0
)
