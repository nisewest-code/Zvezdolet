package app.example.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import app.example.R
import app.example.databinding.FragmentFavoriteBinding
import app.example.model.BasicModel
import app.example.network.ApiHelper
import app.example.network.RetrofitBuilder
import app.example.ui.rv.AdapterRv
import app.example.utils.Resource
import app.example.utils.Status
import app.example.vm.MainViewModel
import app.example.vm.ViewModelFactory

class FavoriteFragment : Fragment() {
    private val binding: FragmentFavoriteBinding by lazy {
        FragmentFavoriteBinding.inflate(layoutInflater)
    }
    private val adapter = AdapterRv()

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[MainViewModel::class.java]
        binding.rv.adapter = adapter

        viewModel.getFavorite().observe(viewLifecycleOwner, this::updateData)
    }

    private fun updateData(data: Resource<List<BasicModel>?>){
        when (data.status) {
            Status.SUCCESS -> {
                binding.rv.visibility = View.VISIBLE
                binding.pb.visibility = View.GONE
                adapter.updateList(data.data ?: listOf())
            }
            Status.ERROR -> {
                binding.rv.visibility = View.VISIBLE
                binding.pb.visibility = View.GONE
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
            }
            Status.LOADING -> {
                binding.rv.visibility = View.GONE
                binding.pb.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FavoriteFragment()
    }
}