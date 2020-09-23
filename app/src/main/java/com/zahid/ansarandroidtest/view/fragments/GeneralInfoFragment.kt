package com.zahid.ansarandroidtest.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.zahid.ansarandroidtest.R
import com.zahid.ansarandroidtest.databinding.FragmentAddCatBinding
import com.zahid.ansarandroidtest.databinding.FragmentGeneralInfoBinding
import com.zahid.ansarandroidtest.viewmodel.MainViewModel


class GeneralInfoFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentGeneralInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_general_info, container, false)
        viewModel.setTitleAppbar("General information")
        observers()
        return binding.root
    }


    fun observers(){

    }
    // Override this method to customize back press
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Override this method to customize back press
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_generalInfoFragment_to_addCatFragment)
        }

    }
}