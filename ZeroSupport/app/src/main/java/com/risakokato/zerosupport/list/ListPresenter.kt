package com.risakokato.zerosupport.list

import com.risakokato.zerosupport.model.BelongingsRepository
import com.risakokato.zerosupport.model.entity.BelongingsRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListPresenter(
        private val fragment: ListContract.View,
        private val belongingsRepository: BelongingsRepository,
        private val scope: CoroutineScope
) : ListContract.Presenter {
    override fun deleteItem(item: BelongingsRoom) = scope.launch(Dispatchers.Default) {
        belongingsRepository.delete(item)
        setList()
    }

    override fun setList() = scope.launch(Dispatchers.Main) {
        fragment.updateList(belongingsRepository.getAll())
    }
}