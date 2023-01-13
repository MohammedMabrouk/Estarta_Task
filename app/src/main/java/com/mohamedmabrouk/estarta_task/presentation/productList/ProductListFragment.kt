package com.mohamedmabrouk.estarta_task.presentation.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohamedmabrouk.estarta_task.R
import com.mohamedmabrouk.estarta_task.databinding.FragmentProductListBinding
import com.mohamedmabrouk.estarta_task.source.model.Product
import com.mohamedmabrouk.estarta_task.utils.Constants.PRODUCT_BUNDLE
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : Fragment(), ProductListAdapter.ProductItemClickListener {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.productList.observe(viewLifecycleOwner, Observer<List<Product?>?> { list ->
            bindAsteroidList(list)
        })

        lifecycleScope.launch {
            viewModel.getAllProducts()
        }

        return binding.root
    }

    private fun bindAsteroidList(list: List<Product?>?) {
        _binding?.productsRecycler!!.layoutManager = LinearLayoutManager(context)
        val adapter = ProductListAdapter(list, this)
        _binding?.productsRecycler!!.adapter = adapter
    }

    override fun onItemClick(product: Product, view: View) {
        val bundle = bundleOf(PRODUCT_BUNDLE to product)
        Navigation.findNavController(view)
            .navigate(R.id.action_productListFragment_to_productDetailsFragment, bundle)
    }
}