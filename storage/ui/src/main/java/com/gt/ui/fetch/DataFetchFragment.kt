package com.gt.ui.fetch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gt.storage.databinding.FragmentFetchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataFetchFragment : Fragment() {

    private lateinit var binding: FragmentFetchBinding

    private val viewModel: DataFetchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFetchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }
        viewModel.isDataFetched.observe(viewLifecycleOwner) {
            viewModel.saveProducts()
        }

        viewModel.isDataSavedLocally.observe(viewLifecycleOwner) {
            if(it) binding.storageDesc.visibility = View.VISIBLE
        }

        viewModel.fetchProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}