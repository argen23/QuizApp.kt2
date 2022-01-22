package com.example.quizappkt2.kolg.presentation.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizappkt2.kolg.data.local.LocalRepository
import com.example.quizappkt2.kolg.domain.model.History
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor (private val localRepository: LocalRepository) : ViewModel() {

val result = MutableLiveData<List<History>>()

    fun getAllHistories() {
        viewModelScope.launch {
            result.value = localRepository.getAllHistory()
        }
    }

    fun delete(history: History) {
        viewModelScope.launch {
            localRepository.delete(history)
        }
    }

}