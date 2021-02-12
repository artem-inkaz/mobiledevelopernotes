package com.example.mobiledevelopernotes.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopernotes.R
import com.example.mobiledevelopernotes.databinding.FragmentMainBinding
import com.example.mobiledevelopernotes.databinding.FragmentNoteBinding
import com.example.mobiledevelopernotes.models.AppNote
import com.example.mobiledevelopernotes.screens.main.MainAdapter
import com.example.mobiledevelopernotes.screens.main.MainFragmentViewModel
import com.example.mobiledevelopernotes.utilits.APP_ACTIVITY


class NoteFragment : Fragment() {
    // display the content of the notes

    private  var _binding: FragmentNoteBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel: NoteFragmentViewModel
    private lateinit var mCurrentNote: AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNoteBinding.inflate(layoutInflater,container,false)
        //из bundle нужно получить нашу заметку
        // делаем приведение типов as AppNote
        mCurrentNote= arguments?.getSerializable("note") as AppNote
//        return inflater.inflate(R.layout.fragment_main, container, false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization(){
        setHasOptionsMenu(true)
        mBinding.noteText.text = mCurrentNote.text
        mBinding.noteName.text = mCurrentNote.name
        // инициализация ViewModel подключаемся к ЖЦ NoteFragment
        mViewModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
    }
    // создадим меню
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    // клик по кнопочке меню
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // получение item id
        when(item.itemId){
            R.id.btn_delete -> {
            // обращаемся к ViewModel
                mViewModel.delete(mCurrentNote){
                    // после того как задача была выполнена обращаемся к APP_ACTIVITY b переходим в  MainFragment
                    APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment)
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // обнуление связки
        _binding = null

    }


}