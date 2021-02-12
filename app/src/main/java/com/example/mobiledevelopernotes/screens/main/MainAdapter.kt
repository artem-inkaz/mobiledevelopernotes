package com.example.mobiledevelopernotes.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopernotes.R
import com.example.mobiledevelopernotes.models.AppNote
import kotlinx.android.synthetic.main.note_item.view.*

// наследование от RecyclerView.Adapter который должен принять <MainAdapter.MainHolder> какой то holder
// () пустой конструктор
//      1            2           3          10       11       12
class MainAdapter:RecyclerView.Adapter<MainAdapter.MainHolder>() {
    // 14
    private var mListNotes = emptyList<AppNote>()

    // создаем holder, который будет принимать view: View и наследоваться от RecyclerView.ViewHolder
    // и будет передавать туда view который он принял в конструктор
    //      4          5            6             7        8
    class MainHolder(view: View): RecyclerView.ViewHolder(view){
    //  необходимо создать свойство которое будет содержать заметка поле name и текст
    // обращаемся к view
        // 9
        val nameNote: TextView = view.item_note_name
        val textNote: TextView = view.item_note_text
    }
    // нажимаем на MainAdapter с Ctrl+O и выбираем методы которые нужно имплементировать реализовать
    // создание holderв котром будут храниться все наши вьюшки
    //13
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        // создаем View 17
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        // теперь наше view надо отправить в MainHolder и вернуть его
        // 18 передаем view которое сейчас создали шаг 17
        return MainHolder(view)
    }
    //13
    // функция которая будет отрисовывать наш holder в RecyclerView
    override fun onBindViewHolder(holder: MainHolder, position: Int) {
    // здесь инициализируем наши поля в нашем holder 19
    // holder.nameNote,textNote это не текстровые поля а текст view
        holder.nameNote.text = mListNotes[position].name
        holder.textNote.text = mListNotes[position].text

    }
    //13
    // получаем размер нашего листа который бкдет обрабатываться в MainAdapter котоые он будет получать из MainFragment
    // где размещен RecyclerView       16
    override fun getItemCount(): Int = mListNotes.size

    // 15
    // через эту функцию из MainFragment мы будем отправлять сюда какой то List из наших записок list: List<AppNote>
    fun setList(list: List<AppNote>){
        // как только лист приняли мы должны наш внутернний лист mListNotes нашего адаптера инициализировать листом который
        // мы приняли в setList () во фрагменте
        mListNotes = list
        // обновляем наш адаптер
        notifyDataSetChanged()

    }

}