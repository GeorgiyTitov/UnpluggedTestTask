package com.gt.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gt.storage.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: ItemDetailsFragmentArgs by navArgs()

    private val viewModel: ItemDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.productData.observe(viewLifecycleOwner) { product ->
            binding.item.productName.text = product.name
            binding.item.productData.text = product.data.toString()
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.item.progressBar.visibility = View.VISIBLE
            } else {
                binding.item.progressBar.visibility = View.GONE
            }
        }

        viewModel.getProductById(args.itemId)

    }
}
