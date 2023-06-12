package com.valkotova.testassignmentpeopledata.ui.fragments.PeopleList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valkotova.testassignmentpeopledata.data.UserData
import com.valkotova.testassignmentpeopledata.data.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import javax.inject.Inject

@HiltViewModel
class PeoplesListViewModel @Inject constructor (
      val usersRepository: UsersRepository,
      val json: Json
        ) : ViewModel()  {
    private val _list : MutableStateFlow<List<UserData>> = MutableStateFlow(listOf())
    val list : StateFlow<List<UserData>>
        get() = _list.asStateFlow()
    private val _state : MutableStateFlow<ViewModelState> = MutableStateFlow(ViewModelState.Empty)
    val state
        get() = _state.asStateFlow()


    init{
        onRefresh()
    }
    fun onRefresh() {
        viewModelScope.async {
            try {
                _state.emit(ViewModelState.Loading)
                _list.emit(usersRepository.getUsers(1))
                _state.emit(ViewModelState.Empty)
            }catch (t : Throwable) {
                Log.d("error", t.message.toString())
                _state.emit(ViewModelState.ViewModelError(text = t.message.toString()))
                async {
                    delay(3000)
                    _state.emit(ViewModelState.Empty)
                }
            }
        }.start()
    }

    fun makeUserJson(user : UserData) : String{
        return URLEncoder.encode(json.encodeToString(user), "utf-8")
    }

    sealed interface ViewModelState{
        object Empty : ViewModelState
        class ViewModelError(val id : Int? = null, val text : String? = null) : ViewModelState
        object Loading : ViewModelState
    }
}