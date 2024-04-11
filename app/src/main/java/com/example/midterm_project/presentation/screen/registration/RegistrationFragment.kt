package com.example.midterm_project.presentation.screen.registration

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.databinding.FragmentRegistrationBinding
import com.example.midterm_project.presentation.base.BaseFragment
import com.example.midterm_project.presentation.event.registration.RegistrationEvent
import com.example.midterm_project.presentation.state.registration.RegistrationState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {
    private val viewModel: RegistrationViewModel by viewModels()

    override fun bind() {}

    override fun bindListeners() {
        binding.btnRegister.setOnClickListener {
            register()
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationEvents(event = it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.registerState.collect {
                    handleState(it)
                }
            }
        }
    }


    private fun register() {
        viewModel.onEvent(
            RegistrationEvent.Register(
                email = binding.etEmailInput.text.toString(),
                password = binding.etPasswordInput.text.toString(),
                passwordCheck = binding.etPasswordInputCheck.text.toString()
            )
        )
    }

    private fun handleState(state: RegistrationState) {
        binding.progressBar.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.errorMessage?.let {
            toastMessage(it)
            viewModel.onEvent(RegistrationEvent.ResetErrorMessage)
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }


    private fun handleNavigationEvents(event: RegistrationViewModel.RegistrationUIEvent) {
        when (event) {
            is RegistrationViewModel.RegistrationUIEvent.NavigateToLogin -> {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        val action = RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment(
            email = binding.etEmailInput.text.toString(),
            password = binding.etPasswordInput.text.toString()
        )
        findNavController().navigate(action)
    }
}
