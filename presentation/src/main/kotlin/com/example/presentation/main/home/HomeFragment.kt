package com.example.presentation.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.core.extensions.collectWithLifecycle
import com.example.presentation.core.viewState.DataLoadingState
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.main.common.CoursesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel by viewModel<HomeViewModel>()

    private lateinit var binding: FragmentHomeBinding

    private val coursesAdapter = CoursesAdapter(onClick = {})

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.getCourses()
        return FragmentHomeBinding.inflate(inflater).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        observeState()
    }

    private fun setupViews() {
        binding.rvCourses.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coursesAdapter
        }
        binding.groupSort.setOnClickListener {
            viewModel.sortCourses()
        }
        binding.errorPlaceholder.btnRetry.setOnClickListener {
            viewModel.getCourses()
        }
        binding.networkErrorPlaceholder.btnRetry.setOnClickListener {
            viewModel.getCourses()
        }
    }

    private fun observeState() {
        collectWithLifecycle(viewModel.courses) { courses ->
            coursesAdapter.submitCourses(courses) {
                binding.rvCourses.scrollToPosition(0)
            }
        }
        collectWithLifecycle(viewModel.loadingState) { loadingState ->
            binding.errorPlaceholder.root.isVisible         = (loadingState == DataLoadingState.ERROR)
            binding.networkErrorPlaceholder.root.isVisible  = (loadingState == DataLoadingState.NETWORK_ERROR)
            binding.loadingIndicator.isVisible              = (loadingState == DataLoadingState.LOADING)
            binding.rvCourses.isVisible                     = (loadingState == DataLoadingState.SUCCESS)
        }
    }
}