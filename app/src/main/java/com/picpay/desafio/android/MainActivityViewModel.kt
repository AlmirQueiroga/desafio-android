package com.picpay.desafio.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.repository.User
import com.picpay.desafio.android.repository.UserRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel: ViewModel() {

    private val repository = UserRepository()
    val data:LiveData<List<User>> get() = repository.userData

    private val state = MutableLiveData<MainViewModelState>()
    val viewModelState: LiveData<MainViewModelState> get() = state

    init {
        fetchDados()
    }

    private fun fetchDados() = viewModelScope.launch {
        try {
            state.postValue(MainViewModelState.Loading(true))
            repository.fetchDados()
            state.postValue(MainViewModelState.Success)
        }catch (e: Exception){
            state.postValue(MainViewModelState.Error)
        }finally {
            state.postValue(MainViewModelState.Loading(false))
        }
    }

    sealed class MainViewModelState{
        data class Loading(val isLoading: Boolean):MainViewModelState()
        object Error: MainViewModelState()
        object Success: MainViewModelState()
    }

}