package com.aac.superheroe.ui.heroe.listtab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aac.superheroe.databinding.ActivityHeroeTabBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroeTabActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHeroeTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.adapter = MyAdapter(supportFragmentManager)
        binding.tabs.post { binding.tabs.setupWithViewPager(binding.viewpager) }
    }

    private inner class MyAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val iItems = 5

        override fun getItem(position: Int): Fragment {
            var fragment: Fragment? = null
            fragment = TabFragment(position)
            return fragment
        }

        override fun getCount(): Int {
            return iItems
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "Marvel Comics"
                1 -> return "DC Comics"
                2 -> return "Dark Horse Comics"
                3 -> return "NBC - Heroes"
                4 -> return "George Lucas"
                5 -> return "Todos"
            }
            return null
        }

    }
}