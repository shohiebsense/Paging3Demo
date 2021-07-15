package com.shohiebsense.myapplication.ui

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.shohiebsense.myapplication.R
import com.shohiebsense.myapplication.databinding.FragmentCharacterBinding
import com.shohiebsense.myapplication.databinding.ItemCharacterBinding
import com.shohiebsense.myapplication.model.RickMortyCharacter
import com.shohiebsense.myapplication.ui.adapter.PagingLoadStateAdapter
import com.shohiebsense.myapplication.ui.adapter.RickMortyCharacterAdapter
import com.shohiebsense.myapplication.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class CharacterFragment : BaseFragment<FragmentCharacterBinding, CharactersViewModel>(),
	RickMortyCharacterAdapter.CharacterClickListener {
	private val charactersViewModel: CharactersViewModel by viewModels()

	@Inject
	lateinit var characterAdapter: RickMortyCharacterAdapter

	override val layoutId: Int
		get() = R.layout.fragment_character

	override fun getVM(): CharactersViewModel = charactersViewModel

	override fun bindVM(binding: FragmentCharacterBinding, vm: CharactersViewModel) =
		with(binding) {
			with(characterAdapter) {
				rvCharacters.apply {
					postponeEnterTransition()
					viewTreeObserver.addOnPreDrawListener {
						startPostponedEnterTransition()
						true
					}
				}
				rvCharacters.adapter = withLoadStateHeaderAndFooter(
					header = PagingLoadStateAdapter(this),
					footer = PagingLoadStateAdapter(this)
				)

				swipeRefresh.setOnRefreshListener { refresh() }
				characterClickListener = this@CharacterFragment

				with(vm) {
					launchOnLifecycleScope {
						charactersFlow.collectLatest { submitData(it) }
					}
					launchOnLifecycleScope {
						loadStateFlow.collectLatest {
							swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
						}
					}
				}
			}
		}

	override fun onCharacterClicked(binding: ItemCharacterBinding, character: RickMortyCharacter) {
		val extras = FragmentNavigatorExtras(
			binding.ivAvatar to "avatar_${character.id}",
			binding.tvName to "name_${character.id}",
			binding.tvStatus to "status_${character.id}"
		)
	}
}