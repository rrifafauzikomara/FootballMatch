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

    private fun hideToolbarSubtitle() {
        supportActionBar?.subtitle = null
    }

    private fun showToolbar(shouldShow: Boolean) {
        if (shouldShow) toolbar.visibility = View.VISIBLE else toolbar.visibility = View.GONE
    }

    private fun showToolbarBackArrow(shouldShow: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(shouldShow)
    }

    private val navigationListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            invalidateOptionsMenu()
            hideToolbarSubtitle()
            when (destination.id) {
                R.id.leaguesFragment -> {
                    showToolbar(true)
                    showToolbarBackArrow(false)
                }
                R.id.detailLeagueFragment -> {
                    showToolbar(true)
                    showToolbarBackArrow(true)
                }
                else -> {
                    showToolbar(false)
                    showToolbarBackArrow(true)
                }
            }
        }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.leaguesFragment -> {
                finish()
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
}