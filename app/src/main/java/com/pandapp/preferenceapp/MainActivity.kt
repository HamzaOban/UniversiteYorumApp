package com.pandapp.preferenceapp

import android.os.Bundle
import android.provider.CalendarContract.Events
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.pandapp.preferenceapp.databinding.ActivityMainBinding
import com.pandapp.preferenceapp.ui.uni.UniversityFragment
import com.pandapp.preferenceapp.util.appUtil


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        setupActionBarWithNavController(navController, appBarConfiguration)


        navView.setupWithNavController(navController)
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.currentUser != null){
            Log.d("UserName","Truee")
            appUtil.getUserName()
            getUserName()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Firebase.auth.currentUser != null){
            Log.d("UserName","True")
            appUtil.getUserName()
            getUserName()
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    private fun getUserName(){

        FirebaseDatabase.getInstance().getReference("users/${appUtil.getUID()}").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val userName = snapshot.child("userName").value
                val email = snapshot.child("email").value

                binding.navView.getHeaderView(0).findViewById<TextView>(R.id.header_user_name).text = userName.toString()
                binding.navView.getHeaderView(0).findViewById<TextView>(R.id.header_email).text = email.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_logout -> {
            // User chose the "Settings" item, show the app settings UI...
            FirebaseAuth.getInstance().signOut()
            val fragment = UniversityFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.add(fragment,"sdasd")
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            Log.d("itemm","çıktı")
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}