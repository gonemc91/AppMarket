package com.example.sign_up.presentation.signup

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.view.isInvisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.presentation.BaseScreen
import com.example.presentation.args
import com.example.presentation.live.observeEvent
import com.example.presentation.viewBinding
import com.example.presentation.viewModelCreator
import com.example.sign_up.R
import com.example.sign_up.databinding.FragmentSignUpBinding
import com.example.sign_up.domain.entities.SignUpData
import com.example.sign_up.domain.entities.SignUpField
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SignUpFragment: Fragment(R.layout.fragment_sign_up) {
    class Screen(
        val email: String
    ): BaseScreen

    @Inject lateinit var factory: SignUpViewModel.Factory
    private val viewModel by viewModelCreator {
        factory.create(args())
    }
    private val binding by viewBinding<FragmentSignUpBinding>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents(savedInstanceState)
        with(binding){
            observeState()
            setupListeners()
        }
    }

    private fun observeEvents(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            viewModel.initialEmailLiveEventValue.observeEvent(viewLifecycleOwner){
                binding.emailEditText.setText(it)
            }
        }
        viewModel.clearFieldEventValue.observeEvent(viewLifecycleOwner){
            getEditTextByField(it).text.clear()
        }
        viewModel.focusFieldEventValue.observeEvent(viewLifecycleOwner){
            getEditTextByField(it).requestFocus()
            getEditTextByField(it).selectAll()
        }
    }

    private fun FragmentSignUpBinding.observeState(){
        viewModel.stateLiveValue.observe(viewLifecycleOwner){ state ->
            progressBar.isInvisible = !state.showProgressBar
            createAccountButton.isEnabled = state.enableSignUpButton

            cleaUpErrors()
            if (state.fieldErrorMessage != null){
                val textInput = getTextInputByField(state.fieldErrorMessage.first)
                textInput.error = state.fieldErrorMessage.second
                textInput.isErrorEnabled = true
            }

        }
    }


    private fun FragmentSignUpBinding.setupListeners(){
        createAccountButton.setOnClickListener{
            createAccountButton.setOnClickListener {
                viewModel.signUp(createSignUpDate())
            }
            emailEditText.addTextChangedListener { viewModel.clearError(SignUpField.EMAIL) }
            usernameEditText.addTextChangedListener { viewModel.clearError(SignUpField.USERNAME) }
            passwordEditText.addTextChangedListener { viewModel.clearError(SignUpField.PASSWORD) }
            repeatPasswordEditText.addTextChangedListener { viewModel.clearError(SignUpField.REPEAT_PASSWORD) }
        }
    }

    private fun FragmentSignUpBinding.createSignUpDate(): SignUpData{
        return SignUpData(
            email = emailEditText.text.toString(),
            username = usernameEditText.text.toString(),
            password = passwordEditText.text.toString(),
            repeatPassword = repeatPasswordEditText.text.toString()
        )
    }


    private fun FragmentSignUpBinding.cleaUpErrors(){
        emailTextInput.error = null
        usernameTextInput.error = null
        passwordTextInput.error = null
        repeatPasswordTextInput.error = null
        emailTextInput.isErrorEnabled = false
        usernameTextInput.isErrorEnabled = false
        passwordTextInput.isErrorEnabled = false
        repeatPasswordTextInput.isErrorEnabled = false
    }

    private fun getEditTextByField(field: SignUpField): EditText{
        return when(field){
            SignUpField.EMAIL -> binding.emailEditText
            SignUpField.USERNAME -> binding.usernameEditText
            SignUpField.PASSWORD -> binding.passwordEditText
            SignUpField.REPEAT_PASSWORD -> binding.repeatPasswordEditText
        }
    }

    private fun getTextInputByField(field: SignUpField): TextInputLayout{
        return when(field){
            SignUpField.EMAIL -> binding.emailTextInput
            SignUpField.USERNAME -> binding.usernameTextInput
            SignUpField.PASSWORD -> binding.passwordTextInput
            SignUpField.REPEAT_PASSWORD -> binding.repeatPasswordTextInput
        }

    }

}