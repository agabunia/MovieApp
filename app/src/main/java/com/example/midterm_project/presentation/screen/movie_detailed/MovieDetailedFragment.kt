package com.example.midterm_project.presentation.screen.movie_detailed

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.midterm_project.databinding.FragmentMovieDetailedBinding
import com.example.midterm_project.presentation.base.BaseFragment
import com.example.midterm_project.presentation.event.movie_detailed.MovieDetailedEvent
import com.example.midterm_project.presentation.state.movie_detailed.MovieDetailedState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailedFragment :
    BaseFragment<FragmentMovieDetailedBinding>(FragmentMovieDetailedBinding::inflate) {
    private val viewModel: MovieDetailedViewModel by viewModels()
    private val movieArgs: MovieDetailedFragmentArgs by navArgs()

    override fun bind() {
        getMovieDetails(movieArgs.id)
    }

    override fun bindListeners() {
        binding.btnBack.setOnClickListener {
            goBack()
        }
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.movieDetailedState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun getMovieDetails(movieId: Int) {
        viewModel.onEvent(MovieDetailedEvent.FetchMovie(movieId))
    }

    private fun handleState(state: MovieDetailedState) {
        binding.apply {
            tvTitle.text = state.movieDetailed?.title
            tvLanguage.text = state.movieDetailed?.originalLanguage
            tvStatus.text = state.movieDetailed?.status
            tvReleaseDate.text = state.movieDetailed?.releaseDate
            tvRuntime.text = state.movieDetailed?.runtime.toString()
            tvTagline.text = state.movieDetailed?.tagline
            tvOverview.text = state.movieDetailed?.overview
            tvVote.text = state.movieDetailed?.voteAverage.toString()
            tvBudget.text = state.movieDetailed?.budget.toString()
            tvRevenue.text = state.movieDetailed?.revenue.toString()
            Glide.with(requireContext()).load(state.movieDetailed?.poster).into(ivPoster)
        }

        state.errorMessage?.let {
            toastMessage(it)
        }
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun goBack() {
        findNavController().popBackStack()
    }

}
