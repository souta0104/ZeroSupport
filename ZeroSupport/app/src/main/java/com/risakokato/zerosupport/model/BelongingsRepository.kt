package com.risakokato.zerosupport.model

import com.risakokato.zerosupport.model.dao.BelongingsDao
import com.risakokato.zerosupport.model.entity.BelongingsRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BelongingsRepository(private val db: BelongingsDao, private val scope: CoroutineScope) {
    init {
        scope.launch {
        }
    }

    suspend fun getAll(): List<BelongingsRoom> {
        return withContext(scope.coroutineContext) {
            db.findAll()
        }
    }

    suspend fun getTodayList(): List<BelongingsRoom> {
        return withContext(scope.coroutineContext) {
            db.findToday()
        }
    }

    fun delete(item: BelongingsRoom) {
        db.delete(item)
    }

    fun deltetAll() {
        db.deleteAll()
    }

}
