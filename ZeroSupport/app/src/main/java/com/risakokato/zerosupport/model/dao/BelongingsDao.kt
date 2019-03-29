package com.risakokato.zerosupport.model.dao

import androidx.room.*
import com.risakokato.zerosupport.model.entity.BelongingsRoom

@Dao
interface BelongingsDao {
    @Query("SELECT * FROM belongingsRoom")
    fun findAll(): List<BelongingsRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(belongingsRoom: BelongingsRoom)

    @Query("SELECT * FROM belongingsRoom WHERE isToday")
    fun findToday(): List<BelongingsRoom>

    @Delete
    fun delete(belongingsRoom: BelongingsRoom)

    @Update
    fun update(belongingsRoom: BelongingsRoom)

    @Query("DELETE FROM belongingsRoom")
    fun deleteAll()

}

