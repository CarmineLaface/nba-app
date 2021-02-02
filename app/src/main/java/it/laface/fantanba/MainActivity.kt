package it.laface.fantanba

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = CustomFragmentFactory
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.navHostFragment)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)
    }
}
