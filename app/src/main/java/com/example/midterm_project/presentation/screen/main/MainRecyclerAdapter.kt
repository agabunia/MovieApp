package com.example.midterm_project.presentation.screen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midterm_project.databinding.MovieScrollBinding
import com.example.midterm_project.presentation.model.main.Movies

class MainRecyclerAdapter :
    ListAdapter<Movies.Detail, MainRecyclerAdapter.MoviesViewHolder>(MoviesDiffUtil()) {

    class MoviesDiffUtil : DiffUtil.ItemCallback<Movies.Detail>() {
        override fun areItemsTheSame(oldItem: Movies.Detail, newItem: Movies.Detail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movies.Detail, newItem: Movies.Detail): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return MoviesViewHolder(MovieScrollBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentMovie = getItem(position)
        holder.bind(currentMovie)
    }

    var onItemClick: ((Movies.Detail) -> Unit)? = null

    inner class MoviesViewHolder(private val binding: MovieScrollBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context
        fun bind(movie: Movies.Detail) {
            binding.apply {
                tvTitle.text = movie.title
                tvDate.text = movie.releaseDate
                tvVote.text = movie.vote
                Glide.with(context).load(movie.poster).into(ivPoster)
                ivPoster.setOnClickListener {
                    onItemClick?.invoke(movie)
                }
            }
        }
    }
}
