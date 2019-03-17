package com.risakokato.zerosupport.model

import com.risakokato.zerosupport.ext.getToday
import com.risakokato.zerosupport.model.dao.BelongingsDao
import com.risakokato.zerosupport.model.entity.BelongingsRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class BelongingsRepository(val db: BelongingsDao, private val scope: CoroutineScope) {
    init {
        scope.launch {
            db.add(BelongingsRoom(0, "tsdata", Date().getToday(), Date().getToday(), false))
        }
    }

    suspend fun getAll(): List<BelongingsRoom> {
        return withContext(scope.coroutineContext) {
            db.findAll()
        }
    }

}
