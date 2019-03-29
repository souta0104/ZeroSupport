package com.risakokato.zerosupport.main

import com.risakokato.zerosupport.model.BelongingsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainPresenter(
        private val fragment: MainContract.View,
        private val repository: BelongingsRepository,
        private val scope: CoroutineScope
) : MainContract.Presenter {
    override fun checkBoxClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setTodayList() = scope.launch(Dispatchers.Main) {
        val list = repository.getTodayList()
        println(list.size)
        fragment.updateList(list)
    }
}