package com.example.profile.presentation.edit

import com.example.common.Container
import com.example.presentation.BaseViewModel
import com.example.profile.R
import com.example.profile.domain.EditUserNameUseCase
import com.example.profile.domain.GetProfileUseCase
import com.example.profile.domain.entites.Profile
import com.example.profile.presentation.ProfileRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val editUserNameUseCase: EditUserNameUseCase,
    private val getProfileUseCase: GetProfileUseCase,
    private val router: ProfileRouter,
):BaseViewModel(){

    private val savedInProgressFlow = MutableStateFlow(false)
    private val loadUsernameFlow = MutableStateFlow<Container<Unit>>(Container.Pending)

    val stateLiveValue = combine(savedInProgressFlow, loadUsernameFlow, ::merge)
        .toLiveValue()

    val initialUsernameLiveEvent = liveEvent<String>()

    init {
       load()
    }

   fun load() = debounce {
       loadScreenInto(loadUsernameFlow){
           val profile = getProfileUseCase.getProfile()
               .filterIsInstance<Container.Success<Profile>>()
               .first()
               .value
           initialUsernameLiveEvent.publish(profile.username)
       }
   }

    private fun merge(
        savedInProgress: Boolean,
        loadContainer: Container<Unit>,
    ): Container<State> {
        return loadContainer.map {
            State(savedInProgress)
        }
    }


    fun goBack()=debounce {
        router.goBack()
    }

    fun saveUsername(newUsername: String) = debounce{
        if(savedInProgressFlow.value) return@debounce
        viewModelScope.launch {
            savedInProgressFlow.value = true
            try {
                editUserNameUseCase.editUsername(newUsername)
                goBack()
            }catch (e: Exception){
                commonUi.toast(resources.getString(R.string.profile_empty_username))
                savedInProgressFlow.value = false
            }
        }
    }


    class State(
        private val isInSavedProgress: Boolean
    ){
         val showProgress: Boolean get() = isInSavedProgress
         val enableSaveButton: Boolean get() = !isInSavedProgress
    }
}
