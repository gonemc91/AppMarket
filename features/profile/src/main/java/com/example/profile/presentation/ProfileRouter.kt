package com.example.profile.presentation

interface ProfileRouter {


    /**
     * Launch the screen for editing username.
     */
    fun launchEditUsername()

    /**
     * Go back to the previous screen.
     */
    fun goBack()


    /**
     * Close all screen and launch the initial screen
     */
    fun restartApp()

}