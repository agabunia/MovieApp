package com.example.midterm_project.presentation.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.midterm_project.databinding.MovieVerticalListLayoutBinding
import com.example.midterm_project.presentation.model.list.MovieFilterModel

class MovieFilterRecyclerAdapter :
    ListAdapter<MovieFilterModel.MovieFilterModel, MovieFilterRecyclerAdapter.MovieFilterViewHolder>(
        MovieFilterDiffUtil()
    ) {

    class MovieFilterDiffUtil : DiffUtil.ItemCallback<MovieFilterModel.MovieFilterModel>() {
        override fun areItemsTheSame(
            oldItem: MovieFilterModel.MovieFilterModel,
            newItem: MovieFilterModel.MovieFilterModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieFilterModel.MovieFilterModel,
            newItem: MovieFilterModel.MovieFilterModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFilterViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return MovieFilterViewHolder(MovieVerticalListLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: MovieFilterViewHolder, position: Int) {
        val currentMovie = getItem(position)
        holder.bind(currentMovie)
    }

    var onItemClick: ((MovieFilterModel.MovieFilterModel) -> Unit)? = null

    inner class MovieFilterViewHolder(private val binding: MovieVerticalListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context
            fun bind(movie: MovieFilterModel.MovieFilterModel) {
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