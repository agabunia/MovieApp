package com.example.midterm_project.presentation.screen.account

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.midterm_project.databinding.FragmentAccountBinding
import com.example.midterm_project.presentation.base.BaseFragment
import com.example.midterm_project.presentation.event.account.AccountEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>(FragmentAccountBinding::inflate) {
    private val viewModel: AccountViewModel by viewModels()

    override fun bind() {}

    override fun bindListeners() {
        binding.btnBack.setOnClickListener {
            viewModel.onEvent(AccountEvent.NavigateToMain)
        }

        binding.btnLogout.setOnClickListener {
            viewModel.onEvent(AccountEvent.Logout)
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect{
                    handleNavigation(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.token.collect{
                    binding.tvUserEmail.text = it ?: "Error! User not found"
                }
            }
        }
    }

    private fun handleNavigation(event: AccountViewModel.AccountUiEvent) {
        when(event) {
            is AccountViewModel.AccountUiEvent.NavigateToLogin -> navigateToLogin()
            is AccountViewModel.AccountUiEvent.NavigateToMain -> navigateToMain()
        }
    }

    private fun navigateToMain() {
        findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToMainFragment())
    }

    private fun navigateToLogin() {
        findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToLoginFragment("",""))
    }

}