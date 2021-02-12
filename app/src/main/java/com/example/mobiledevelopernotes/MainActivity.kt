package com.example.mobiledevelopernotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mobiledevelopernotes.databinding.ActivityMainBinding
import com.example.mobiledevelopernotes.utilits.APP_ACTIVITY

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    // навигация
    lateinit var navController: NavController
    // создание связки, при закрытии MainActivity должны обнулят нашу ссылку _binding
    private var _binding:ActivityMainBinding? = null
    // сссылка ссылается на наш _binding данная строчка val mBinding get() = _binding!! позволит избежать проверки на null
    // _binding!! - будет 100% не null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // инициализация нашей связки
        _binding = ActivityMainBinding.inflate(layoutInflater)
        // root овый макет
        setContentView(mBinding.root)
        // инициализация глобальной константы APP_ACTIVITY
        // теперь можем получать контекст из любого нашего приложения
        APP_ACTIVITY = this
        // инициализируем mToolbar
        mToolbar = mBinding.toolbar
        // activity_main - fragment сюда будут устанавливаться наши все фрагменеты
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        // установка ToolBar
        setSupportActionBar(mToolbar)
        title = getString(R.string.title)
    }

    //когда удалена наша MainActivity
    override fun onDestroy() {
        super.onDestroy()
    // присваиваем нашей связке null и предотвращаем утечку памяти
        _binding = null
    }
}