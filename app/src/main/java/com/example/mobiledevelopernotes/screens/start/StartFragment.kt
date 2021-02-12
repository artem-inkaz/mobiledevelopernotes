package com.example.mobiledevelopernotes.screens.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mobiledevelopernotes.R
import com.example.mobiledevelopernotes.databinding.FragmentStartBinding
import com.example.mobiledevelopernotes.utilits.*
import kotlinx.android.synthetic.main.fragment_start.*

// Describe database connections

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    // создаем общедоступную переменную
    private val mBinding get() = _binding!!
    // создаем экземпляр StartFragmentViewModel
    private lateinit var mViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // инициализация mBinding
        _binding = FragmentStartBinding.inflate(layoutInflater,container, false)
//        return inflater.inflate(R.layout.fragment_start, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization(){
      // инициализация ViewModel и привязываем ViewModel к жизненному циклу нашего StartFragment
        mViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)
        // клик и передаем типы
        mBinding.btnRoom.setOnClickListener {
            mViewModel.initDatabase(TYPE_ROOM){
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }

        mBinding.btnFirebase.setOnClickListener {
            mBinding.inputEmail.visibility =View.VISIBLE
            mBinding.inputPassword.visibility =View.VISIBLE
            mBinding.btnLogin.visibility =View.VISIBLE
            mBinding.btnLogin.setOnClickListener {
                val inputEmail = mBinding.inputEmail.text.toString()
                val inputPassword = mBinding.inputPassword.text.toString()
                if(inputEmail.isNotEmpty() && inputPassword.isNotEmpty()){
                    EMAIL = inputEmail
                    PASSWORD = inputPassword
                    mViewModel.initDatabase(TYPE_FIREBASE) {
//                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                            showToast("INIT OK")
                    }
                } else  {
                        showToast(getString(R.string.toast_wrong_enter))
                    }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}