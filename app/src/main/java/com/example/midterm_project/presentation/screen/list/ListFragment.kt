package com.example.midterm_project.presentation.screen.list

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
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
            navigateToMain()
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
            navigateToMovieDetailedFragment(it.id)
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

        state.errorMessage?.let {
            toastMessage(it)
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