package com.example.midterm_project.presentation.screen.list

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.midterm_project.databinding.FragmentListBinding
import com.example.midterm_project.presentation.base.BaseFragment
import com.example.midterm_project.presentation.event.list.ListEvent
import com.example.midterm_project.presentation.state.list.ListState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {
    private val viewModel: ListViewModel by viewModels()
    private lateinit var genresRecyclerAdapter: GenresRecyclerAdapter
    private lateinit var movieFilterRecyclerAdapter: MovieFilterRecyclerAdapter

    override fun bind() {
        setGenresAdapter()
        setMovieFilterAdapter()
    }

    override fun bindListeners() {
        binding.btnBack.setOnClickListener {
            viewModel.onEvent(ListEvent.NavigateToMain)
        }

        binding.btnSearch.setOnClickListener {
            val searchTitle = binding.etSearch.text.toString()
            if (searchTitle.isNotEmpty()) {
                viewModel.onEvent(ListEvent.FetchSearchedMovies(title = searchTitle))
                binding.etSearch.text?.clear()
            }
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listState.collect {
                    handleState(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationEvent(it)
                }
            }
        }
    }

    private fun setGenresAdapter() {
        genresRecyclerAdapter = GenresRecyclerAdapter()
        genresRecyclerAdapter.onItemClick = {
            if (it.isClicked) {
                viewModel.onEvent(ListEvent.FetchMovies(id = it.id))
            } else {
                viewModel.onEvent(ListEvent.FetchMovies())
            }
        }
        binding.apply {
            genresRecycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            genresRecycler.setHasFixedSize(true)
            genresRecycler.adapter = genresRecyclerAdapter
        }
        viewModel.onEvent(ListEvent.FetchGenres)
    }

    private fun setMovieFilterAdapter() {
        movieFilterRecyclerAdapter = MovieFilterRecyclerAdapter()
        binding.apply {
            movieListRecycler.layoutManager = LinearLayoutManager(requireContext())
            movieListRecycler.setHasFixedSize(true)
            movieListRecycler.adapter = movieFilterRecyclerAdapter
        }
        movieFilterRecyclerAdapter.onItemClick = {
            viewModel.onEvent(ListEvent.NavigateToDetailed(id = it.id))
        }
        viewModel.onEvent(ListEvent.FetchMovies())
    }


    private fun handleState(state: ListState) {
        state.movies?.let {
            movieFilterRecyclerAdapter.submitList(it)
        }

        state.genres?.let {
            genresRecyclerAdapter.submitList(it)
        }

        binding.progressBar.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE

        state.errorMessage?.let {
            toastMessage(it)
            viewModel.onEvent(ListEvent.ResetErrorMessage)
        }
    }

    private fun handleNavigationEvent(event: ListViewModel.ListUIEvent) {
        when (event) {
            is ListViewModel.ListUIEvent.NavigateToDetailed -> navigateToMovieDetailedFragment(id = event.id)
            is ListViewModel.ListUIEvent.NavigateToMain -> navigateToMain()
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToMovieDetailedFragment(id: Int) {
        val action = ListFragmentDirections.actionListFragmentToMovieDetailedFragment(id)
        findNavController().navigate(action)
    }

    private fun navigateToMain() {
        findNavController().navigate(ListFragmentDirections.actionListFragmentToMainFragment())
    }

}