package com.example.cuidarmais.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cuidarmais.R
import com.example.cuidarmais.databinding.FragmentCreateUserBinding
import com.example.cuidarmais.ui.viewmodels.UserViewModel
import com.example.cuidarmais.ui.viewmodels.UserViewModelFactory

class CreateUserFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(requireActivity().application)
    }
    private var _binding: FragmentCreateUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBack.setOnClickListener { findNavController().navigate(R.id.loginFragment) }

        binding.btnSalvar.setOnClickListener {
            val name = binding.editName.text.toString().trim()
            val email = binding.editEmail.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()

            if (name.isEmpty()) binding.editName.error = "Please enter your name"
            if (email.isEmpty()) binding.editEmail.error = "Please enter your email"
            if (password.isEmpty()) binding.editPassword.error = "Please enter a passworld"
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) return@setOnClickListener

            userViewModel.user.observe(viewLifecycleOwner) { user ->
                user?.let {
                    findNavController()
                        .navigate(CreateUserFragmentDirections.actionCreateUserFragmentToAppFragment())
                }
            }
            userViewModel.create(name, email, password)
        }
    }

}