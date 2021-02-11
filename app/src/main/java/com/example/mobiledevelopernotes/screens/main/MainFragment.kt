package com.example.mobiledevelopernotes.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopernotes.R
import com.example.mobiledevelopernotes.databinding.FragmentMainBinding
import com.example.mobiledevelopernotes.utilits.APP_ACTIVITY

class MainFragment : Fragment() {
    // describe notes

    private  var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(layoutInflater,container,false)
//        return inflater.inflate(R.layout.fragment_main, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization(){
        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        // подключаем слушатель
        mBinding.btnAddNote.setOnClickListener {
            APP_ACTIVITY.mNavController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // обнуление связки
        _binding = null
    }
}