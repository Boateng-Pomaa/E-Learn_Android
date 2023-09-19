package com.example.e_learn

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.e_learn.databinding.ActivityHomeBinding
import com.example.e_learn.ui.login.ui.PostQuestion.PostQuestionFragment
import com.example.e_learn.ui.login.ui.mathTopics.SetsFragment
import com.example.e_learn.ui.login.ui.profile.HostFragment
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

            binding.appBarHome.fab.visibility = View.INVISIBLE

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navHost:NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_home) as NavHostFragment
         navController =navHost.navController

        supportFragmentManager.addOnBackStackChangedListener {
            // Update floating action button actions based on the current fragment
            updateFloatingActionButtonActions()
        }

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,R.id.nav_subject, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                return true
            }
            R.id.postQues -> {
                    val myFragment = PostQuestionFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_home, myFragment)
                        .addToBackStack("postback")
                        .commit()

                return true
            }
            R.id.profile -> {
                val myFragment = HostFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_content_home, myFragment)
                    .addToBackStack("profileback")
                    .commit()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onSupportNavigateUp(): Boolean {
       return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    fun updateFloatingActionButtonVisibility() {
        val currentFragment = getCurrentFragment()
        // Determine if the FAB should be visible based on the current fragment
        val isFabVisible = when (currentFragment) {
            is PostQuestionFragment -> true
            is SetsFragment -> true
            else -> false
        }
        // Set FAB visibility
        binding.appBarHome.fab.visibility = if (isFabVisible) View.VISIBLE else View.GONE
    }
     fun updateFloatingActionButtonActions(){
        when (getCurrentFragment()) {
            is SetsFragment -> {
                binding.appBarHome.fab.setOnClickListener {
                        val alert = AlertDialog.Builder(this)
                        alert.setTitle("READY TO TAKE THIS QUIZ?")
                        alert.setMessage("ARE YOU SURE YOU ARE READY TO TAKE THIS QUIZ?\nMake sure you have covered the topics in order to do this!")
                        alert.setPositiveButton("YES"){
                                _, _: Int -> navController.navigate(R.id.action_nav_setsFragment_to_nav_setQuiz)
                        }
                        alert.setNegativeButton("NO"){
                                _, _: Int ->
                            Toast.makeText(this,"Good choice,learning makes a man perfect",
                                Toast.LENGTH_SHORT).show()
                        }
                            .create().show()
                    }
                binding.appBarHome.fab.visibility = View.VISIBLE
            }
            is PostQuestionFragment -> {
                binding.appBarHome.fab.setOnClickListener {
                   navController.navigate(R.id.nav_slideshow)
                }
                binding.appBarHome.fab.visibility = View.VISIBLE
            }
            else -> {
                // Hide FAB for other fragments
                binding.appBarHome.fab.visibility = View.GONE
            }
            // Add cases for other fragments as needed

        }
    }
    private fun getCurrentFragment(): Fragment? {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_home)
        return navHostFragment?.childFragmentManager?.primaryNavigationFragment
    }
    fun setAppBarTitle(title: String) {
        supportActionBar?.title = title
    }

}