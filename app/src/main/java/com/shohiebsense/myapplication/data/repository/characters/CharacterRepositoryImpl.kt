package com.shohiebsense.myapplication.data.repository.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shohiebsense.myapplication.model.datasource.CharactersPagingDataSource
import com.shohiebsense.myapplication.model.RickMortyCharacter
import com.shohiebsense.myapplication.model.api.CharacterApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CharacterRepositoryImpl @Inject constructor(
    private val service: CharacterApi,
) : CharacterRepository {

    override suspend fun getAllCharacters(): Flow<PagingData<RickMortyCharacter>> = Pager(
        config = PagingConfig(pageSize = 34, prefetchDistance = 1),
        pagingSourceFactory = { CharactersPagingDataSource(service) }
    ).flow

    override suspend fun getSinglecharacter(id: Int) {
        return service.getSingleCharacter(id)
    }


    override suspend fun getMultipleCharacters(ids: List<Int>) = service.getMultipleCharacters(ids)

    override suspend fun filterCharacters(
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ) = service.filterCharacters(name, status, species, type, gender)
}