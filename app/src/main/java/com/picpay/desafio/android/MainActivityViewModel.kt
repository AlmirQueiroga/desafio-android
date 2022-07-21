package com.picpay.desafio.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel: ViewModel() {

    private val repository = UserRepository()
    val data:LiveData<List<User>> get() = repository.users

    private val state = MutableLiveData<MainViewModelState>()
    val viewModelState: LiveData<MainViewModelState> get() = state

    init {
        fetchDados()
    }

    private fun fetchDados() = viewModelScope.launch {
        try {
            updateState(MainViewModelState.Loading(true))
            repository.fetchDados()
        }catch (e: Exception){
            updateState(MainViewModelState.Error)
        }finally {
            updateState(MainViewModelState.Sucess)
        }
    }

    private suspend fun updateState(newState: MainViewModelState){
        withContext(Dispatchers.Main){
            state.value = newState
        }
    }

    sealed class MainViewModelState{
        data class Loading(val isLoading: Boolean):MainViewModelState()
        object Error: MainViewModelState()
        object Sucess: MainViewModelState()
    }

}