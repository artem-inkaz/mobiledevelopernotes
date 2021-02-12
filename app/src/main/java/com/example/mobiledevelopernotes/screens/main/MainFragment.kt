package com.example.mobiledevelopernotes.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopernotes.R
import com.example.mobiledevelopernotes.databinding.FragmentMainBinding
import com.example.mobiledevelopernotes.models.AppNote
import com.example.mobiledevelopernotes.utilits.APP_ACTIVITY

class MainFragment : Fragment() {
    // describe notes

    private  var _binding: FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: MainFragmentViewModel
    // для RecyclerView
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainAdapter
    // объект observe который будет подключаться к нашей LiveData и когда будем закрывать наш фрагмент будем его отключать
    private lateinit var mObserverList: Observer<List<AppNote>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        mAdapter = MainAdapter()
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter
        mObserverList = Observer {
           // создаем лист и будем получать лист it из LiveData
            // перевернем чтобы записки добавлялись на верх а не вниз RecyclerView
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        //--------------------------------
        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        //------ после того как сделали подключение к адаптеру код выше
        // здесь LiveData под наблюдением
        mViewModel.allNotes.observe(this,mObserverList)
        // подключаем слушатель
        mBinding.btnAddNote.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // обнуление связки
        _binding = null
        //--------------
        mViewModel.allNotes.removeObserver(mObserverList)
        mRecyclerView.adapter = null
    }

    // чтобы из MainAdapter не создавать экземляр MainFragment
    companion object{
        fun click(note: AppNote){
            // нужно передать нашу заметку из MainFragment в NoteFragment
            val bundle = Bundle()
            // т.к. это наш bundle не стандартный добавляем putSerializable
            // data class AppNote  делаем Serializable
            // в bundle положили нашу заметку
            bundle.putSerializable("note",note)
            // переименовали mNavController
            // т.к. приставка m означает что мы не имеем доступа из других классов к этой переменной
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment,bundle)

        }
    }
}