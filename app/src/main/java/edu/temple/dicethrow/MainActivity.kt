package edu.temple.dicethrow

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    private val dieViewModel = DieViewModel by lazy {
        ViewModelProvider(this)[DieViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dieFragment1 = DieFragment.newInstance(6)  // 6-sided die
        val dieFragment2 = DieFragment.newInstance(20) // 20-sided die

        // Add the fragments dynamically to the container views
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView1, dieFragment1)
            .replace(R.id.fragmentContainerView2, dieFragment2)
            .commit()

        val rollButton: Button = findViewById(R.id.rollDiceButton)
        rollButton.setOnClickListener {
            dieViewModel.rollDie()
        }

    }
}
