package com.arief.news.ui.business

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.arief.news.R
import com.arief.news.adapter.NewsAdapter
import com.arief.news.databinding.FragmentBusinessBinding
import com.arief.news.utils.Status
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class BusinessFragment : Fragment() {

    private lateinit var binding: FragmentBusinessBinding
    private val viewModel : BusinessViewModel by viewModel()
    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_business,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NewsAdapter(listOf(), this@BusinessFragment)
        binding.rvData.adapter = adapter
        binding.rvData.layoutManager = LinearLayoutManager(requireContext())

        binding.searchText.addTextChangedListener {
            adapter.filterData(it.toString())
        }
        observer()
    }

    private fun observer(){

        viewModel.news.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.mainShimmer.apply {
                        stopShimmer()
                        visibility = View.GONE
                    }

                    binding.rvData.visibility = View.VISIBLE
                    adapter.setData(it.data!!)
                }
                Status.LOADING -> {
                    binding.mainShimmer.apply {
                        startShimmer()
                        visibility = View.VISIBLE
                    }
                }
                Status.ERROR -> {
                    binding.mainShimmer.apply {
                        stopShimmer()
                        visibility = View.GONE
                    }
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}