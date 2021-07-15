package com.shohiebsense.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shohiebsense.myapplication.databinding.ItemCharacterBinding
import com.shohiebsense.myapplication.model.RickMortyCharacter
import javax.inject.Inject

class RickMortyCharacterAdapter @Inject constructor() : PagingDataAdapter<RickMortyCharacter, RickMortyCharacterAdapter.CharacterViewHolder>(RickMortyCharacterComparator) {

	var characterClickListener: CharacterClickListener? = null


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		CharacterViewHolder(
			ItemCharacterBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)

	override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
		getItem(position)?.let { holder.bind(it) }
	}

	inner class CharacterViewHolder(private val binding: ItemCharacterBinding) :
		RecyclerView.ViewHolder(binding.root) {

		init {
			itemView.setOnClickListener {
				characterClickListener?.onCharacterClicked(
					binding,
					getItem(absoluteAdapterPosition) as RickMortyCharacter
				)
			}
		}

		fun bind(item: RickMortyCharacter) = with(binding) {
			ViewCompat.setTransitionName(binding.ivAvatar, "avatar_${item.id}")
			ViewCompat.setTransitionName(binding.tvName, "name_${item.id}")
			ViewCompat.setTransitionName(binding.tvStatus, "status_${item.id}")
			rickmortycharacter = item
		}
	}

	object RickMortyCharacterComparator : DiffUtil.ItemCallback<RickMortyCharacter>() {
		override fun areItemsTheSame(oldItem: RickMortyCharacter, newItem: RickMortyCharacter) =
			oldItem.id == newItem.id

		override fun areContentsTheSame(oldItem: RickMortyCharacter, newItem: RickMortyCharacter) =
			oldItem == newItem
	}


	interface CharacterClickListener {
		fun onCharacterClicked(binding: ItemCharacterBinding, character: RickMortyCharacter)
	}



}
