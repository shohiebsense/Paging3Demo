package com.shohiebsense.myapplication.model.api

import com.shohiebsense.myapplication.model.apiresponse.PagedResponse
import com.shohiebsense.myapplication.model.RickMortyCharacter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

	@GET("character/")
	suspend fun getAllCharacters(@Query("page") page: Int): Response<PagedResponse<RickMortyCharacter>>

	suspend fun getSingleCharacter(id: Int)

	suspend fun getMultipleCharacters(ids: List<Int>)

	suspend fun filterCharacters(
		name: String,
		status: String,
		species: String,
		type: String,
		gender: String
	)
}