package com.rifafauzi.footballmatch.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.rifafauzi.footballmatch.R
import com.rifafauzi.footballmatch.databinding.ActivityMainBinding
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.mainContent)

        initMain()
        setupToolbar()
        setupNavController()
        setupBottomNavigation()
        openSearchMatch()

    }

    private fun initMain() {
        navController = Navigation.findNavController(this, R.id.mainContent)
        toolbar = findViewById(R.id.mainToolbar)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
    }

    private fun setupNavController() {
        navController.addOnDestinationChangedListener(navigationListener)
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(navigationListener)
    }

    private fun hideToolbarSubtitle() {
        supportActionBar?.subtitle = null
    }

    private fun showToolbar(shouldShow: Boolean) {
        if (shouldShow) toolbar.visibility = View.VISIBLE else toolbar.visibility = View.GONE
    }

    private fun showToolbarBackArrow(shouldShow: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(shouldShow)
    }

    private fun showBottomNavigation(shouldShow: Boolean) {
        if (shouldShow) binding.bottomNavigationView.visibility =
            View.VISIBLE else binding.bottomNavigationView.visibility = View.GONE
    }

    private val navigationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            invalidateOptionsMenu()
            hideToolbarSubtitle()
            when (destination.id) {
                R.id.playerDetailFragment -> {
                    showToolbar(true)
                    hideImageSearch()
                    showToolbarBackArrow(true)
                    showBottomNavigation(false)
                }
                R.id.detailTeamFragment -> {
                    showToolbar(true)
                    hideImageSearch()
                    showToolbarBackArrow(true)
                    showBottomNavigation(false)
                }
                R.id.leaguesFragment -> {
                    showToolbar(true)
                    hideImageSearch()
                    showToolbarBackArrow(false)
                    showBottomNavigation(true)
                }
                R.id.favoriteFragment -> {
                    showToolbar(true)
                    hideImageSearch()
                    showToolbarBackArrow(false)
                    showBottomNavigation(true)
                }
                R.id.detailLeagueFragment -> {
                    showToolbar(true)
                    showImageSearch()
                    showToolbarBackArrow(true)
                    showBottomNavigation(false)
                }
                R.id.nextMatchFragment -> {
                    showToolbar(true)
                    hideImageSearch()
                    showToolbarBackArrow(true)
                    showBottomNavigation(false)
                }
                R.id.previousFragment -> {
                    showToolbar(true)
                    hideImageSearch()
                    showToolbarBackArrow(true)
                    showBottomNavigation(false)
                }
                R.id.detailMatchFragment -> {
                    showToolbar(true)
                    hideImageSearch()
                    showToolbarBackArrow(true)
                    showBottomNavigation(false)
                }
                R.id.searchMatchFragment -> {
                    showToolbar(true)
                    hideImageSearch()
                    showToolbarBackArrow(true)
                    showBottomNavigation(false)
                }
                else -> {
                    showToolbar(false)
                    hideImageSearch()
                    showToolbarBackArrow(false)
                    showBottomNavigation(false)
                }
            }
        }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.leaguesFragment -> {
                finish()
            }
            R.id.favoriteFragment -> {
                binding.bottomNavigationView.selectedItemId = R.id.leaguesFragment
            }
            else -> {
                navController.navigateUp()
            }
        }
    }

    override fun onSupportNavigateUp() : Boolean {
        navController.navigateUp()
        return true
    }

    private fun showImageSearch() {
        binding.imgSearch.visibility = View.VISIBLE
    }

    private fun hideImageSearch() {
        binding.imgSearch.visibility = View.GONE
    }

    private fun openSearchMatch() {
        binding.imgSearch.setOnClickListener {
            navController.navigate(R.id.searchMatchFragment)
        }
    }
}