package com.example.midterm_project.presentation.screen.main

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midterm_project.databinding.FragmentMainBinding
import com.example.midterm_project.presentation.base.BaseFragment
import com.example.midterm_project.presentation.event.main.MainEvent
import com.example.midterm_project.presentation.state.main.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var movieFragmentRecyclerAdapter: MovieFragmentRecyclerAdapter

    override fun bind() {
        setAdapter()
    }

    override fun bindListeners() {
        binding.btnAllMovies.setOnClickListener {
            movieList()
        }

        binding.btnAccount.setOnClickListener {
            account()
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainState.collect {
                    handleState(state = it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationEvents(it)
                }
            }
        }
    }

    private fun setAdapter() {
        movieFragmentRecyclerAdapter = MovieFragmentRecyclerAdapter()
        movieFragmentRecyclerAdapter.onMovieItemClickListener = {
            viewModel.onEvent(MainEvent.NavigateToDetailed(id = it.id))
        }
        binding.apply {
            movieFragmentRecycler.layoutManager = LinearLayoutManager(requireContext())
            movieFragmentRecycler.setHasFixedSize(true)
            movieFragmentRecycler.adapter = movieFragmentRecyclerAdapter
        }
        viewModel.onEvent(
            MainEvent.FetchMovies(
                listOf(
                    "Now Playing" to "now_playing",
                    "Popular" to "popular",
                    "Top Rated" to "top_rated",
                    "Upcoming" to "upcoming"
                )
            )
        )
    }

    private fun movieList() {
        viewModel.onEvent(MainEvent.MovieList)
    }

    private fun account() {
        viewModel.onEvent(MainEvent.Account)
    }

    private fun handleState(state: MainState) {
        binding.progressBar.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.movieFragmentList?.let {
            movieFragmentRecyclerAdapter.submitList(it)
        }

        state.errorMessage?.let {
            toastMessage(it)
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun handleNavigationEvents(event: MainViewModel.MainUIEvent) {
        when (event) {
            is MainViewModel.MainUIEvent.NavigateToAccount -> navigateToAccount()
            is MainViewModel.MainUIEvent.NavigateToMovieList -> navigateToMovieListFragment()
            is MainViewModel.MainUIEvent.NavigateToDetailed -> navigateToMovieDetailedFragment(id = event.id)
        }
    }

    private fun navigateToAccount() {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToAccountFragment())
    }

    private fun navigateToMovieDetailedFragment(id: Int) {
        val action = MainFragmentDirections.actionMainFragmentToMovieDetailedFragment(id)
        findNavController().navigate(action)
    }

    private fun navigateToMovieListFragment() {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToListFragment())
    }
}
