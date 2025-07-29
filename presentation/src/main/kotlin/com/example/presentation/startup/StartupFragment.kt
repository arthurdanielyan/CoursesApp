package com.example.presentation.startup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.presentation.R
import com.example.presentation.core.extensions.collectWithLifecycle
import com.example.presentation.databinding.FragmentStartupBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartupFragment : Fragment() {

    private val viewModel: StartupViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentStartupBinding.inflate(inflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectUiEvents()
    }

    private fun collectUiEvents() {
        collectWithLifecycle(viewModel.uiEvent) {
            when (it) {
                StartupUiEvent.ShowErrorToast -> {
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