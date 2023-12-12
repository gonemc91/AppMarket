package com.example.navigation.presentation

import com.example.navigation.domain.repositories.GetCartItemCountRepository
import com.example.navigation.domain.repositories.GetCurrentUsernameRepository
import com.example.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    getCurrentUsernameRepository: GetCurrentUsernameRepository,
    getCartItemCountRepository: GetCartItemCountRepository,
    private val router: MainRouter
): BaseViewModel(){


}