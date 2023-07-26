package app.example.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.example.databinding.FragmentHomeBinding
import app.example.model.BasicModel
import app.example.network.ApiHelper
import app.example.network.RetrofitBuilder
import app.example.ui.rv.AdapterRv
import app.example.utils.Resource
import app.example.utils.Status
import app.example.vm.MainViewModel
import app.example.vm.ViewModelFactory


class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private val adapter = AdapterRv()

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        )[MainViewModel::class.java]

        viewModel.liveDataSearch().observe(viewLifecycleOwner, this::updateData)

        binding.rv.adapter = adapter

        binding.editText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (s.length < 2){
                    binding.editText.error = "Short"
                } else {
                    binding.editText.error = null
                    viewModel.researchData(s.toString())

                }
            }

        })
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
            HomeFragment()
    }
}