package com.example.midterm_project.presentation.screen.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm_project.R
import com.example.midterm_project.databinding.GenresLayoutBinding
import com.example.midterm_project.presentation.model.list.GenresModel
import com.example.midterm_project.presentation.model.list.MovieFilterModel

class GenresRecyclerAdapter :
    ListAdapter<GenresModel.Genres, GenresRecyclerAdapter.GenresViewHolder>(GenresDiffUtil()) {

    class GenresDiffUtil : DiffUtil.ItemCallback<GenresModel.Genres>() {
        override fun areItemsTheSame(
            oldItem: GenresModel.Genres,
            newItem: GenresModel.Genres
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GenresModel.Genres,
            newItem: GenresModel.Genres
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return GenresViewHolder(GenresLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val currentGenre = getItem(position)
        holder.bind(currentGenre)
    }

    var onItemClick: ((GenresModel.Genres) -> Unit)? = null

    inner class GenresViewHolder(private val binding: GenresLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: GenresModel.Genres) {
            binding.apply {
                tvGenre.text = genre.name
                tvGenre.setOnClickListener {
                    genre.isClicked = !genre.isClicked
                    onItemClick?.invoke(genre)
                    if (genre.isClicked) {
                        layoutGenre.setBackgroundResource(R.drawable.genre_shape_active)
                        tvGenre.setTextColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.white
                            )
                        )
                    } else {
                        layoutGenre.setBackgroundResource(R.drawable.genre_shape_inactive)
                        tvGenre.setTextColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.black
                            )
                        )
                    }
                }
            }
        }
    }
}