package com.picpay.desafio.android

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var usersAdapter: UserListAdapter
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        usersAdapter = UserListAdapter()
        binding.apply {
            recyclerView.apply {
                adapter = usersAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
        viewModel.data.observe(this, {
            usersAdapter.users = it
        })

        viewModel.viewModelState.observe(this, { state ->
            when (state) {
                is MainViewModel.MainViewModelState.Loading -> {
                    binding.userListProgressBar.isVisible = state.isLoading
                }
                MainViewModel.MainViewModelState.Error -> {
                    binding.userListProgressBar.isVisible = false
                    showErrorMsg()
                }
                MainViewModel.MainViewModelState.Sucess -> {
                    binding.userListProgressBar.isVisible = false
                }
            }
        })
    }

    private fun showErrorMsg(){
        val message = getString(R.string.error)
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }
}