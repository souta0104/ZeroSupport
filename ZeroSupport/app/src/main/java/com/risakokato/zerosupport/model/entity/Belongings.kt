package com.risakokato.zerosupport.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "belongingsRoom")
data class BelongingsRoom(
        @PrimaryKey(autoGenerate = true)
        val id: Long,
        var belongings: String,
        var date: String,
        var updateDate: String,
        var isChecked: Boolean,
        var isToday: Boolean
)