package com.risakokato.zerosupport.model

import com.risakokato.zerosupport.model.dao.BelongingsDao
import com.risakokato.zerosupport.model.entity.BelongingsRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

class BelongingsRepository(val db: BelongingsDao, private val scope: CoroutineScope) {
    init {

    }

    suspend fun getAll(): List<BelongingsRoom> {
        return withContext(scope.coroutineContext) {
            db.findAll()
        }
    }

    fun deltetAll() {
        db.deleteAll()
    }

}
