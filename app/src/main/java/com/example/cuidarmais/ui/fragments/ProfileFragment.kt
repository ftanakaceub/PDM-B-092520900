package com.example.cuidarmais.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.cuidarmais.R
import com.example.cuidarmais.data.room.models.User
import com.example.cuidarmais.databinding.FragmentProfileBinding
import com.example.cuidarmais.ui.viewmodels.UserViewModel
import com.example.cuidarmais.ui.viewmodels.UserViewModelFactory
import kotlin.getValue


class ProfileFragment : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels {
        UserViewModelFactory(requireActivity().application)
    }
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.editName.setText(user.name)
                binding.editEmail.setText(user.email)

                binding.saveButton.setOnClickListener {
                    if (binding.editName.text.isEmpty()) binding.editName.error =
                        "Please input your name"
                    if (binding.editEmail.text.isEmpty()) binding.editEmail.error =
                        "Please input your email"
                    if (binding.editPassword.text.isEmpty()) binding.editPassword.error =
                        "Please input your password"
                    if (binding.editName.text.isEmpty() || binding.editEmail.text.isEmpty() || binding.editPassword.text.isEmpty()) return@setOnClickListener

                    val newUser = User(
                        user.id,
                        binding.editName.text.toString().trim(),
                        binding.editEmail.text.toString().trim(),
                        binding.editPassword.text.toString().trim()
                    )
                    userViewModel.update(newUser)
                    Toast.makeText(requireContext(), "Profile updated", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}