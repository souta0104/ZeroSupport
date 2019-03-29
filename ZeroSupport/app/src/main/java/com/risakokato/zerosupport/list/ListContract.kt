package com.risakokato.zerosupport.list

import com.risakokato.zerosupport.ext.IntentType
import com.risakokato.zerosupport.model.entity.BelongingsRoom
import kotlinx.coroutines.Job

interface ListContract {
    interface View {
        fun intent(intentType: IntentType)
        fun updateList(list: List<BelongingsRoom>)
    }

    interface Presenter {
        fun setList(): Job
        fun deleteItem(item: BelongingsRoom): Job
    }
}