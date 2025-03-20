package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class DieFragment : Fragment() {

    private val dieSIDE = "side_number"
    private val currentROLE = "current_role"
    private lateinit var dieTextView: TextView
    private var dieSides: Int = 6
    private var currentRole: Int? = null
    private lateinit var dieViewModel: DieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dieSides = it.getInt(dieSIDE, 6)
        }
        savedInstanceState?.let {
            currentRole = it.getInt(currentROLE)
        }
        dieViewModel = ViewModelProvider(requireActivity())[DieViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dieViewModel.getCurrentROLL().observe(viewLifecycleOwner) {
            dieTextView.text = it.toString()
        }
        if (dieViewModel.getCurrentROLL().value == null)
            dieViewModel.rollDie()
    }
    companion object {
        fun newInstance(sides: Int = 6) =
            DieFragment().apply {
                arguments = Bundle().apply {
                    putInt(dieSIDE, sides)
                }
            }
    }

}




