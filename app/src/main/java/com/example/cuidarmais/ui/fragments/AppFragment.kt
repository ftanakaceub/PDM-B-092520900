package com.example.cuidarmais.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.cuidarmais.R
import com.example.cuidarmais.databinding.FragmentAppBinding
import com.example.cuidarmais.ui.adapters.AppPagerAdapater
import com.example.cuidarmais.ui.viewmodels.UserViewModel
import com.example.cuidarmais.ui.viewmodels.UserViewModelFactory


class AppFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(requireActivity().application)
    }
    private var _binding: FragmentAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
        setupBottomNavigation()
    }

    private fun setupViewPager() {
        val adapter = AppPagerAdapater(this)
        binding.viewPager.adapter = adapter

        // Callback para quando o usuário arrastar a tela (Swipe)
        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Sincroniza o BottomNav com a página atual
                when (position) {
                    0 -> binding.bottomNav.selectedItemId = R.id.homeFragment
                    1 -> binding.bottomNav.selectedItemId = R.id.listMedicineFragment
                    2 -> binding.bottomNav.selectedItemId = R.id.profileFragment
                }
            }
        })
    }

    private fun setupBottomNavigation() {
        // Callback para quando o usuário clicar no BottomNav
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    binding.viewPager.currentItem = 0
                    true
                }
                R.id.listMedicineFragment -> {
                    binding.viewPager.currentItem = 1
                    true
                }
                R.id.profileFragment -> {
                    binding.viewPager.currentItem = 2
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}