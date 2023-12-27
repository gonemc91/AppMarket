package com.example.profile.presentation.pofile

import com.example.common.Container
import com.example.presentation.BaseViewModel
import com.example.profile.domain.GetProfileUseCase
import com.example.profile.domain.LogoutUseCase
import com.example.profile.presentation.ProfileRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val logout: LogoutUseCase,
    private val router: ProfileRouter,
): BaseViewModel() {

    val profileLiveValue = getProfileUseCase.getProfile()
        .toLiveValue(Container.Pending)

    fun editProfile() = debounce{
        router.launchEditUsername()
    }

    fun reload() = debounce {
        router.restartApp()
    }

    fun logout() = debounce {
        viewModelScope.launch{
            logout.logout()
            router.restartApp()
        }
    }


}