package com.gt.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gt.domain.product.Product
import com.gt.storage.databinding.ItemBinding

class ProductAdapter(
    private val productList: List<Product>,
    private val onItemClick: (String) -> Unit = {}
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        val productDisplayText = "${product.id}: ${product.name}"
        holder.binding.productName.text = productDisplayText
        holder.binding.root.setOnClickListener {
            onItemClick(product.id)
        }
    }

    override fun getItemCount(): Int = productList.size
}
