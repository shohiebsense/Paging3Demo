package com.shohiebsense.myapplication.data.repository.characters

import androidx.paging.PagingData
import com.shohiebsense.myapplication.model.RickMortyCharacter
import kotlinx.coroutines.flow.Flow


interface CharacterRepository {
	suspend fun getAllCharacters() : Flow<PagingData<RickMortyCharacter>>
	suspend fun getSinglecharacter(id: Int)
	suspend fun getMultipleCharacters(ids: List<Int>)
	suspend fun filterCharacters(
		name: String,
		status: String,
		species: String,
		type: String,
		gender: String
	)
}