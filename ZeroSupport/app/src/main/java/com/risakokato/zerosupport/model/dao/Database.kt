package com.risakokato.zerosupport.model.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.risakokato.zerosupport.model.entity.BelongingsRoom

@Database(entities = [BelongingsRoom::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun belongingsDao(): BelongingsDao
}