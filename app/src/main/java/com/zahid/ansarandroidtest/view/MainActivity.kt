package com.zahid.ansarandroidtest.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.zahid.ansarandroidtest.R
import com.zahid.ansarandroidtest.databinding.ActivityMainBinding
import com.zahid.ansarandroidtest.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()
    var context: Context? = null
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        context = this@MainActivity
        var navController = Navigation.findNavController(this, R.id.fragment)
        setUpBackButton()
        observers()
    }
    private fun setUpBackButton() {
        setSupportActionBar(binding.appBar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        //Setting  Custom menu button
        binding.appBar.toolbar.post(Runnable {
            val d =
                ResourcesCompat.getDrawable(resources, R.drawable.back_button_drawable, null)
            binding.appBar.toolbar.navigationIcon = d
        })
        binding.appBar.toolbar.setNavigationOnClickListener { this.onBackPressed() }
    }

    fun observers(){
        viewModel.isLoading().observe(this, Observer {
            it?.let {
                if (it){
                    binding.progressBar1.visibility = View.VISIBLE
                }else{
                    binding.progressBar1.visibility = View.GONE
                }
            }
        })
        viewModel.titleOfAppbar.observe(this, Observer {
            binding.appBar.tvTitle.text = it
        })
    }

}