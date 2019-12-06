package com.rifafauzi.footballmatch.ui.player

import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.repository.player.PlayerRepository
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-12-06.
 */
 
class PlayerListViewModel @Inject constructor(private val repository: PlayerRepository) : BaseViewModel()