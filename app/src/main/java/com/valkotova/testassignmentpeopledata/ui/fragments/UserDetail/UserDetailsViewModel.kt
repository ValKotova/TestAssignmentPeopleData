package com.valkotova.testassignmentpeopledata.ui.fragments.UserDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.valkotova.testassignmentpeopledata.data.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor (
    private val state: SavedStateHandle,
    private val json : Json
) : ViewModel()  {
    private val _data : MutableStateFlow<UserData?> = MutableStateFlow(null)
    val data : StateFlow<UserData?>
        get() = _data.asStateFlow()
    init{
        viewModelScope.launch {
            val userJson = state.get<String>("user")
            _data.emit(
                json.decodeFromString(URLDecoder.decode(userJson, "utf-8"))
            )
        }
    }
    private val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    fun getBirthday(date : LocalDateTime) : String{
        return date.format(formatter)
    }
}