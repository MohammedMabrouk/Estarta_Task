package com.mohamedmabrouk.estarta_task.presentation.productList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mohamedmabrouk.estarta_task.BR
import com.mohamedmabrouk.estarta_task.R
import com.mohamedmabrouk.estarta_task.databinding.LayoutProductItemBinding
import com.mohamedmabrouk.estarta_task.source.model.Product

class ProductListAdapter(
    private val productsItemsList: List<Product?>?,
    private val productItemsClickListener: ProductItemClickListener
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productItem = productsItemsList!![position]!!

        holder.container.setOnClickListener {
            productItemsClickListener.onItemClick(productItem, it)
        }

        holder.bind(productItem)
    }

    override fun getItemCount(): Int {
        return productsItemsList?.size ?: 0
    }

    class ViewHolder(binding: LayoutProductItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: LayoutProductItemBinding

        val container: ConstraintLayout = itemView.findViewById(R.id.cl_container)

        init {
            this.binding = binding
        }

        fun bind(product: Product) {
            binding.setVariable(BR.product, product)
            binding.executePendingBindings()
        }
    }

    interface ProductItemClickListener {
        fun onItemClick(product: Product, view: View)
    }
}