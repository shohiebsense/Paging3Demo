package com.shohiebsense.myapplication.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shohiebsense.myapplication.data.repository.characters.CharacterRepository
import com.shohiebsense.myapplication.model.RickMortyCharacter
import com.shohiebsense.myapplication.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(val characterRepository: CharacterRepository) : BaseViewModel(){

	private lateinit var _charactersFlow: Flow<PagingData<RickMortyCharacter>>
	val charactersFlow: Flow<PagingData<RickMortyCharacter>>
		get() = _charactersFlow

	init {
		getAllCharacters()
	}

	private fun getAllCharacters() = launchPagingAsync({
		characterRepository.getAllCharacters().cachedIn(viewModelScope)
	}, {
		_charactersFlow = it
	})

}