package com.mohamedmabrouk.estarta_task.presentation.productDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mohamedmabrouk.estarta_task.R
import com.mohamedmabrouk.estarta_task.databinding.FragmentProductDetailsBinding
import com.mohamedmabrouk.estarta_task.databinding.FragmentProductListBinding
import com.mohamedmabrouk.estarta_task.source.model.Product
import com.mohamedmabrouk.estarta_task.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    lateinit var product: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        product = arguments?.get(Constants.PRODUCT_BUNDLE) as Product
        binding.product = product
        binding.lifecycleOwner = this
        return binding.root
    }

}