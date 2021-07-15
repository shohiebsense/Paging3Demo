package com.shohiebsense.myapplication.model.datasource

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shohiebsense.myapplication.model.RickMortyCharacter
import com.shohiebsense.myapplication.model.api.CharacterApi

class CharactersPagingDataSource(private val service: CharacterApi) : PagingSource<Int, RickMortyCharacter>() {

	override fun getRefreshKey(state: PagingState<Int, RickMortyCharacter>): Int? {
		return 1
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickMortyCharacter> {
		val pageNumber = params.key ?: 1
		return try{
			val response = service.getAllCharacters(pageNumber)
			val pagedResponse = response.body()
			val data = pagedResponse?.results

			var nextPageNumber : Int? = null
			if(pagedResponse?.pageInfo?.next != null){
				val uri = Uri.parse(pagedResponse.pageInfo.next)
				val nextPageQuery = uri.getQueryParameter("page")
				nextPageNumber = nextPageQuery?.toInt()
			}
			LoadResult.Page(
				data = data.orEmpty(),
				prevKey = null,
				nextKey =  nextPageNumber
			)
		}catch (e: Exception){
			LoadResult.Error(e)
		}

	}


}