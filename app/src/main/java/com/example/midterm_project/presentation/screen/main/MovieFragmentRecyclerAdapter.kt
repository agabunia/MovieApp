package com.example.midterm_project.presentation.screen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_project.databinding.MoviesFragmentLayoutBinding
import com.example.midterm_project.presentation.model.main.Movies
import com.example.midterm_project.presentation.model.main.MoviesFragmentList

class MovieFragmentRecyclerAdapter() :
    ListAdapter<MoviesFragmentList, MovieFragmentRecyclerAdapter.MovieFragmentViewHolder>(
        MoviesDiffUtil()
    ) {

    class MoviesDiffUtil : DiffUtil.ItemCallback<MoviesFragmentList>() {
        override fun areItemsTheSame(
            oldItem: MoviesFragmentList,
            newItem: MoviesFragmentList
        ): Boolean {
            return oldItem.fragmentNameJson == newItem.fragmentNameJson
        }

        override fun areContentsTheSame(
            oldItem: MoviesFragmentList,
            newItem: MoviesFragmentList
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFragmentViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return MovieFragmentViewHolder(MoviesFragmentLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: MovieFragmentViewHolder, position: Int) {
        val fragment = getItem(position)
        holder.bind(fragment)
    }

    var onMovieItemClickListener: ((Movies.Detail) -> Unit)? = null

    inner class MovieFragmentViewHolder(private val binding: MoviesFragmentLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val movieAdapter = MainRecyclerAdapter()

        init {
            binding.movieListRecycler.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = movieAdapter
            }
        }

        fun bind(movieFragment: MoviesFragmentList) {
            binding.apply {
                tvListTitle.text = movieFragment.fragmentName
                movieAdapter.submitList(movieFragment.fragmentMovieList.results)
                movieAdapter.onItemClick = { movie ->
                    onMovieItemClickListener?.invoke(movie)
                }
            }
        }
    }
}
