package com.gt.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gt.common.R
import com.gt.domain.product.Product
import com.gt.storage.databinding.FragmentSearchResultsBinding
import com.gt.ui.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultsFragment : Fragment() {

    private lateinit var binding: FragmentSearchResultsBinding

    private val viewModel: SearchResultsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.foundProducts.observe(viewLifecycleOwner) { foundProducts ->
            if (foundProducts.isNotEmpty()) {
                binding.noSearchResultsTextView.visibility = View.GONE
                setupSearchResults(foundProducts)
            } else {
                binding.noSearchResultsTextView.visibility = View.VISIBLE
            }
        }

        retrieveQuery()?.let {
            viewModel.searchProducts(it)
        }
    }

    private fun retrieveQuery(): String? {
        val queryKey = requireActivity().getString(R.string.query_key)
        return requireActivity().intent.extras?.getString(queryKey)
    }

    private fun setupSearchResults(foundProducts: List<Product>) {
        val recyclerView = binding.productRv
        val productAdapter = ProductAdapter(foundProducts) { productId ->
            findNavController().navigate(
                SearchResultsFragmentDirections.actionToItemDetails(productId)
            )
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = productAdapter
        val dividerItemDecoration =
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}
