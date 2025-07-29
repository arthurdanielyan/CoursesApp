package com.example.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.core.extensions.collectWithLifecycle
import com.example.presentation.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentLoginBinding.inflate(inflater).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCallbacks()
        observeState()
        collectUiEvents()
    }

    private fun setupCallbacks() {
        binding.etEmail.doAfterTextChanged {
            viewModel.onEmailType(it.toString())
        }
        binding.etPassword.doAfterTextChanged {
            viewModel.onPasswordType(it.toString())
        }
        binding.btnVk.setOnClickListener {
            viewModel.onVkClick()
        }
        binding.btnOk.setOnClickListener {
            viewModel.onOkClick()
        }
        binding.btnLogin.setOnClickListener {
            viewModel.onLoginClick()
        }
    }

    private fun observeState() {
        collectWithLifecycle(viewModel.isLoginButtonEnabled) {
            binding.btnLogin.isEnabled = it
        }
    }

    private fun collectUiEvents() {
        collectWithLifecycle(viewModel.uiEvent) {
            when (it) {
                LoginUiEvent.ShowErrorToast -> {
                    Toast.makeText(
                        requireContext(),
                        R.string.unknown_error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}