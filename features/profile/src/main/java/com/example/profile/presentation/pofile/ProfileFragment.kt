package com.example.profile.presentation.pofile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.presentation.viewBinding
import com.example.presentation.views.observe
import com.example.profile.R
import com.example.profile.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment @Inject constructor():Fragment(R.layout.fragment_profile) {

    private val viewModel by viewModels<ProfileViewModel>()

    private val binding by viewBinding<FragmentProfileBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            setupListeners()
            observeProfile()
        }
    }


    private fun FragmentProfileBinding.setupListeners(){
        editProfileButton.setOnClickListener { viewModel.editProfile() }
        logoutButton.setOnClickListener { viewModel.logout() }
        root.setTryAgainListener { viewModel.reload() }
    }

    private fun FragmentProfileBinding.observeProfile(){
      root.observe(viewLifecycleOwner, viewModel.profileLiveValue){profile->
          emailField.text = profile.email
          usernameField.text = profile.username
          createdAtField.text = profile.createdAtString
      }
    }


}