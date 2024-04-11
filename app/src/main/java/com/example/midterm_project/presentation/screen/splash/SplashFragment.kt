package com.example.midterm_project.presentation.screen.splash


import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.midterm_project.databinding.FragmentSplashBinding
import com.example.midterm_project.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: SplashViewModel by viewModels()

    override fun bind() {}

    override fun bindListeners() {}

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    onEvent(event = it)
                }
            }
        }
    }

    private fun onEvent(event: SplashViewModel.SplashEvent) {
        when (event) {
            is SplashViewModel.SplashEvent.NavigateToLogin -> navigateToLogin()
            is SplashViewModel.SplashEvent.NavigateToMain -> navigateToMain()
        }
    }

    private fun navigateToLogin() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment("", ""))
    }

    private fun navigateToMain() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
    }

}