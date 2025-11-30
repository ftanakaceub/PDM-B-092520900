package com.example.cuidarmais.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cuidarmais.R
import com.example.cuidarmais.databinding.FragmentLoginBinding
import com.example.cuidarmais.ui.viewmodels.UserViewModel
import com.example.cuidarmais.ui.viewmodels.UserViewModelFactory

class LoginFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(requireActivity().application)
    }
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val email = binding.editEmail.text.toString().trim()
            val password = binding.editPassword.text.toString().trim()

            if (email.isEmpty()) binding.editEmail.error = "Please enter your email"
            if (password.isEmpty()) binding.editPassword.error = "Please enter a passworld"
            if (email.isEmpty() || password.isEmpty()) return@setOnClickListener

            userViewModel.user.observe(viewLifecycleOwner) { user ->
                user?.let {
                    findNavController()
                        .navigate(LoginFragmentDirections.actionLoginFragmentToAppFragment())
                }
            }
            userViewModel.authenticate(email, password)
        }

        binding.btnNewUser.setOnClickListener {
            findNavController()
                .navigate(
                    LoginFragmentDirections.actionLoginFragmentToCreateUserFragment()
                )
        }
    }

}