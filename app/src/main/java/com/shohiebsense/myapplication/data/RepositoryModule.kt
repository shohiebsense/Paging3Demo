package com.shohiebsense.myapplication.data

import com.shohiebsense.myapplication.data.repository.characters.CharacterRepository
import com.shohiebsense.myapplication.data.repository.characters.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
	@Binds
	abstract fun bindCharacterRepository(
		characterRepositoryImpl: CharacterRepositoryImpl
	): CharacterRepository

	/*@Binds
	abstract fun bindEpisodeRepository(
		episodeRepositoryImpl: EpisodeRepositoryImpl
	): EpisodeRepository

	@Binds
	abstract fun bindLocationRepository(
		locationRepositoryImpl: LocationRepositoryImpl
	): LocationRepository*/
}