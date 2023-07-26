package app.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import app.example.R
import app.example.databinding.ActivityMainBinding
import app.example.ui.fragment.FavoriteFragment
import app.example.ui.fragment.HomeFragment
import app.example.vm.MainViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val firstFragment = HomeFragment.newInstance()
        val secondFragment = FavoriteFragment.newInstance()

        setCurrentFragment(firstFragment)

        binding.navBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> setCurrentFragment(firstFragment)
                R.id.favorite -> setCurrentFragment(secondFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(binding.viewPager.id, fragment)
            commit()
        }
}