package com.rifafauzi.footballmatch.ui.detailmatch

import com.rifafauzi.footballmatch.base.BaseViewModel
import com.rifafauzi.footballmatch.repository.match.MatchRepository
import javax.inject.Inject

/**
 * Created by rrifafauzikomara on 2019-11-24.
 */
 
class DetailMatchViewModel @Inject constructor(private val repository: MatchRepository) : BaseViewModel()