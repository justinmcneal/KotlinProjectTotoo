package com.example.kotlinproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        bottomNavigationView = findViewById(R.id.bottomnavigationview)

        setCurrentfragment(HomePagee()) //BYPASS THE MAINACTIVITY AND GO TO HOMEPAGEE
        bottomNavigationView.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.ic_home -> setCurrentfragment(HomePagee())
                R.id.ic_analytics -> setCurrentfragment( Analytics())
                R.id.ic_completedTask -> setCurrentfragment(CompletedTask())
                R.id.ic_profile -> setCurrentfragment(UserProfile())

                else->{}
            }
            true

        }

    }
    private fun setCurrentfragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flMainFragment, fragment)
            commit()
        }
}