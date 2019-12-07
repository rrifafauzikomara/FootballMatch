package com.rifafauzi.footballmatch.ui.detailmatch


import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.base.BaseFragment
import com.rifafauzi.footballmatch.common.Result
import com.rifafauzi.footballmatch.databinding.FragmentDetailMatchBinding
import com.rifafauzi.footballmatch.db.FavoriteMatch
import com.rifafauzi.footballmatch.db.database
import com.rifafauzi.footballmatch.model.match.Match
import com.rifafauzi.footballmatch.utils.NEXT_MATCH
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class DetailMatchFragment : BaseFragment<FragmentDetailMatchBinding, DetailMatchViewModel>() {

    override fun getLayoutResourceId() = R.layout.fragment_detail_match
    override fun getViewModelClass() = DetailMatchViewModel::class.java

    private lateinit var idEvent: String

    private var type: String? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var imageHome: String? = null
    private var imageAway: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        //Enable cross fade transition on glide
        val transition = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        arguments?.let {
            val safeArgs = DetailMatchFragmentArgs.fromBundle(it)
            idEvent = safeArgs.idEvent

            type = if (type.equals(NEXT_MATCH)) {
                safeArgs.type
            } else {
                safeArgs.type
            }
        }

        favoriteState()
        vm.getDetailMatch(idEvent)
        vm.detailMatch.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideMatch()
                        showLoading()
                    }
                    is Result.HasData -> {
                        showMatch()
                        hideLoading()
                        displayData(it.data)
                    }
                    is Result.NoData -> {
                        hideMatch()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.empty_data))
                    }
                    is Result.Error -> {
                        hideMatch()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.unknown_error))
                    }
                    is Result.NoInternetConnection -> {
                        hideMatch()
                        hideLoading()
                        longSnackBar(resources.getString(R.string.no_connection))
                    }
                }
            }
        })

        vm.teamHome.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideImage()
                        showProgressImage()
                    }
                    is Result.HasData -> {
                        showImage()
                        hideProgressImage()
                        imageHome = it.data[0].strTeamBadge
                        displayTeamLogo(imageHome, binding.homeImg, transition)
                    }
                    is Result.NoData -> {
                        hideImage()
                        hideProgressImage()
                    }
                    is Result.Error -> {
                        hideImage()
                        hideProgressImage()
                    }
                    is Result.NoInternetConnection -> {
                        hideImage()
                        hideProgressImage()
                    }
                }
            }
        })

        vm.awayTeam.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Loading -> {
                        hideImage()
                        showProgressImage()
                    }
                    is Result.HasData -> {
                        showImage()
                        hideProgressImage()
                        imageAway = it.data[0].strTeamBadge
                        displayTeamLogo(imageAway, binding.awayImg, transition)
                    }
                    is Result.NoData -> {
                        hideImage()
                        hideProgressImage()
                    }
                    is Result.Error -> {
                        hideImage()
                        hideProgressImage()
                    }
                    is Result.NoInternetConnection -> {
                        hideImage()
                        hideProgressImage()
                    }
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_match, menu)
        menuItem = menu
        setFavorite()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun favoriteState(){
        context?.database?.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
                .whereArgs("(ID_EVENT = {id})",
                    "id" to idEvent)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite(){
        try {
            context?.database?.use {
                insert(
                    FavoriteMatch.TABLE_FAVORITE,
                    FavoriteMatch.ID_EVENT to idEvent,
                    FavoriteMatch.LEAGUE_NAME to binding.data?.strLeague,
                    FavoriteMatch.DATE_EVENT to binding.data?.dateEvent,
                    FavoriteMatch.HOME_TEAM_BADGE to imageHome,
                    FavoriteMatch.HOME_TEAM_NAME to binding.data?.strHomeTeam,
                    FavoriteMatch.HOME_GOAL_DETAIL to binding.data?.strHomeGoalDetails,
                    FavoriteMatch.HOME_SCORE to binding.data?.intHomeScore,
                    FavoriteMatch.AWAY_SCORE to binding.data?.intAwayScore,
                    FavoriteMatch.AWAY_TEAM_BADGE to imageAway,
                    FavoriteMatch.AWAY_TEAM_NAME to binding.data?.strAwayTeam,
                    FavoriteMatch.AWAY_GOAL_DETAIL to binding.data?.strAwayGoalDetails,
                    FavoriteMatch.HOME_LINEUP_GOAL_KEEPER to binding.data?.strHomeLineupGoalkeeper,
                    FavoriteMatch.AWAY_LINEUP_GOAL_KEEPER to binding.data?.strAwayLineupGoalkeeper,
                    FavoriteMatch.HOME_LINEUP_DEFENSE to binding.data?.strHomeLineupDefense,
                    FavoriteMatch.AWAY_LINEUP_DEFENSE to binding.data?.strAwayLineupDefense,
                    FavoriteMatch.HOME_LINEUP_MIDFIELD to binding.data?.strHomeLineupMidfield,
                    FavoriteMatch.AWAY_LINEUP_MIDFIELD to binding.data?.strAwayLineupMidfield,
                    FavoriteMatch.HOME_LINEUP_FORWARD to binding.data?.strHomeLineupForward,
                    FavoriteMatch.AWAY_LINEUP_FORWARD to binding.data?.strAwayLineupForward,
                    FavoriteMatch.HOME_LINEUP_SUBSTITUTES to binding.data?.strHomeLineupSubstitutes,
                    FavoriteMatch.AWAY_LINEUP_SUBSTITUTES to binding.data?.strAwayLineupSubstitutes,
                    FavoriteMatch.TYPE to type
                )
            }
            snackBar(resources.getString(R.string.add_to_favorite))
        } catch (e: SQLiteConstraintException){
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            context?.database?.use {
                delete(
                    FavoriteMatch.TABLE_FAVORITE,
                    "(ID_EVENT = {id})",
                    "id" to idEvent
                )
            }
            snackBar(resources.getString(R.string.delete_from_favorite))
            findNavController().navigateUp()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(requireContext(), e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_add_to_favorites)
    }

    private fun displayData(data: List<Match>) {
        with(data[0]){
            binding.data = this
            vm.getDetailTeamHome(idHomeTeam)
            vm.getDetailTeamAway(idAwayTeam)
        }
    }

    private fun displayTeamLogo(teamUrl: String?, teamImage: ImageView, transition: DrawableCrossFadeFactory) {
        Glide.with(requireContext()).load(teamUrl)
            .transition(DrawableTransitionOptions.withCrossFade(transition))
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_trophy)
                    .error(R.drawable.ic_error))
            .into(teamImage)
    }

    private fun showLoading() {
        binding.showLoading = true
    }

    private fun hideLoading() {
        binding.showLoading = false
    }

    private fun showMatch() {
        binding.showData = true
    }

    private fun hideMatch() {
        binding.showData = false
    }

    private fun showProgressImage() {
        binding.showProgress = true
    }

    private fun hideProgressImage() {
        binding.showProgress = false
    }

    private fun showImage() {
        binding.showImage = true
    }

    private fun hideImage() {
        binding.showImage = false
    }
}