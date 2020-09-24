package com.sportsinteractive.cricket

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sportsinteractive.cricket.model.STATUS


class SquadViewModel @ViewModelInject constructor(private val response: Response) : ViewModel() {
    val liveDataResult = liveData {
        emit(ResultData(STATUS.LOADING))
        emit(response.getResponse())
    }
}
