package com.zahid.ansarandroidtest.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.zahid.ansarandroidtest.R
import com.zahid.ansarandroidtest.adapters.CustomAdapter
import com.zahid.ansarandroidtest.databinding.FragmentAddCatBinding
import com.zahid.ansarandroidtest.model.Result
import com.zahid.ansarandroidtest.utils.Status
import com.zahid.ansarandroidtest.viewmodel.MainViewModel
import kotlin.collections.ArrayList


class AddCatFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentAddCatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_cat, container, false)
        viewModel.setTitleAppbar("Adding")
        viewModel.requestCategories()
        observers()
        return binding.root
    }


    fun observers() {
        viewModel.allCategoriesMut.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                when (it.status) {
                    Status.Status.SUCCESS -> {
                        it.data?.result?.let { it1 -> initExpListView(it1) }
                    }
                    Status.Status.ERROR -> {
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }

    fun initExpListView(list: ArrayList<Result>) {
        //CREATE AND BIND TO ADAPTER
        val adapter = CustomAdapter(requireContext(), list)
        binding.expListView.setAdapter(adapter)
        //SET ONCLICK LISTENER
        binding.expListView.setOnChildClickListener { parent, v, groupPos, childPos, id ->

            Navigation.findNavController(binding.root)
                .navigate(R.id.action_addCatFragment_to_generalInfoFragment)
//            Toast.makeText(

//                getApplicationContext(),
//                team[groupPos].players.get(childPos),
//                Toast.LENGTH_SHORT
//            ).show()
            false
        }
    }

    // Override this method to customize back press
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Override this method to customize back press
        requireActivity().onBackPressedDispatcher.addCallback(this) {


        }

    }
}