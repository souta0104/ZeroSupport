package com.risakokato.zerosupport.main

import com.risakokato.zerosupport.model.entity.BelongingsRoom
import kotlinx.coroutines.Job

interface MainContract {
    interface View {
        fun updateList(list: List<BelongingsRoom>)
    }

    interface Presenter {
        fun setTodayList(): Job
        fun checkBoxClick()
    }
}