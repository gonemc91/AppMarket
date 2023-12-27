package com.example.profile.presentation.edit

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.presentation.live.observeEvent
import com.example.presentation.viewBinding
import com.example.presentation.views.observe
import com.example.profile.R
import com.example.profile.databinding.FragmentEditProfileBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditProfileFragment:Fragment(R.layout.fragment_edit_profile) {

    private val binding by viewBinding<FragmentEditProfileBinding>()

    private val viewModel by viewModels<EditProfileViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            observeState()
            observeEvent(savedInstanceState)
            setupListeners()


        }

    }


    private fun FragmentEditProfileBinding.observeEvent(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            viewModel.initialUsernameLiveEvent.observeEvent(viewLifecycleOwner){
                usernameInputText.setText(it)
            }
        }
    }

    private fun FragmentEditProfileBinding.observeState(){
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue){state->
            saveButton.isEnabled = state.enableSaveButton
            progressBar.isVisible = state.showProgress
        }
    }


   private fun FragmentEditProfileBinding.setupListeners(){
       saveButton.setOnClickListener {
           viewModel.saveUsername(usernameInputText.text.toString())
       }
       cancelButon.setOnClickListener {
           viewModel.goBack()
       }
       root.setTryAgainListener {
           viewModel.load()
       }
   }

}