package com.pandapp.preferenceapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pandapp.preferenceapp.databinding.ActivityMainBinding
import com.pandapp.preferenceapp.ui.auth.login.LoginFragment
import com.pandapp.preferenceapp.ui.auth.register.RegisterFragment
import com.pandapp.preferenceapp.ui.uni.UniversityFragment
import com.pandapp.preferenceapp.ui.uni.UniversityViewModel
import com.pandapp.preferenceapp.util.appUtil


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel : UniversityViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("UserName","True1")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_uni,R.id.nav_comment
            ), drawerLayout
        )
        viewModel.getUserInfo()
        viewModel.userInfo.observe(this, Observer {
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.header_user_name).text = it.userName
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.header_email).text = it.email
        })
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.header_user_name).text = UniversityFragment.user.userName
        binding.navView.getHeaderView(0).findViewById<TextView>(R.id.header_email).text = UniversityFragment.user.email
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    override fun onStart() {

        super.onStart()
        Log.d("UserName","True2")

        if (Firebase.auth.currentUser != null){
            appUtil.getUserName()

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val fragmentList = supportFragmentManager.fragments
        var handled = false
        for (f in fragmentList) {
            if (f is LoginFragment) {
                handled = (f as LoginFragment).onBackPressed()
                if (handled) {
                    break
                }
            }
        }
        if(!handled) {
            super.onBackPressed();
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("UserName","True3")
        if (Firebase.auth.currentUser != null){
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.header_user_name).text = UniversityFragment.user.userName
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.header_email).text = UniversityFragment.user.email

            appUtil.getUserName()

        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("UserName","True4")
        if (Firebase.auth.currentUser != null){
            appUtil.getUserName()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        if (Firebase.auth.currentUser != null){
            Log.d("UserName","True5")
            appUtil.getUserName()

        }
    }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean{
       return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}