package com.example.e_learn

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.e_learn.databinding.ActivityHomeBinding
import com.example.e_learn.ui.login.ui.PostQuestion.PostQuestionFragment
import com.example.e_learn.ui.login.ui.profile.HostFragment
import com.example.e_learn.ui.login.ui.profile.ProfileFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

        binding.appBarHome.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navHost:NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_home) as NavHostFragment
         navController =navHost.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
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
                // Handle click for Menu Item 1
                return true
            }
            R.id.postQues -> {
                // Handle click for Menu Item 2
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
            // Add more cases for other menu items if needed
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onSupportNavigateUp(): Boolean {
       return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    fun setAppBarTitle(title: String) {
        supportActionBar?.title = title
    }
//    fun showfab2(){
//
//        binding.appBarHome.fab.visibility = View.GONE
//        binding.appBarHome.floatingBtn.visibility = View.VISIBLE
//    }
}