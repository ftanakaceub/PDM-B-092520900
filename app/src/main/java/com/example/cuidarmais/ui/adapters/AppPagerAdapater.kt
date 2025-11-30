package com.example.cuidarmais.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cuidarmais.ui.fragments.HomeFragment
import com.example.cuidarmais.ui.fragments.ListMedicineFragment
import com.example.cuidarmais.ui.fragments.ProfileFragment

class AppPagerAdapater(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ListMedicineFragment()
            2 -> ProfileFragment()
            else -> HomeFragment()
        }
    }
}