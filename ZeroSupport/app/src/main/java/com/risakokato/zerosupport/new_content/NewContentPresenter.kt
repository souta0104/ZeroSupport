package com.risakokato.zerosupport.new_content

import com.risakokato.zerosupport.model.BelongingsRepository
import kotlinx.coroutines.CoroutineScope

class NewContentPresenter(private val belongingsRepository: BelongingsRepository, private val scope: CoroutineScope) : NewContentContract.Presenter {

}