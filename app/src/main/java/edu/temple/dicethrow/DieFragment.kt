package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlin.random.Random

class DieFragment : Fragment() {

    private val DIESIDE = "sidenumber"
    private val PREVIOUS_ROLE= "previousrole"
    private var currentROLE= 0
    private lateinit var dieTextView: TextView
    private var dieSides: Int = 6

    // Factory method to create an instance of DieFragment with specified die sides
    companion object {
        fun newInstance(sides: Int = 6): DieFragment {
            val fragment = DieFragment()
            val args = Bundle()
            args.putInt(DIESIDE, sides)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
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
        super.onViewCreated(view, savedInstanceState)

        // Prompt user for die sides

        promptForDieSides()

        if(savedInstanceState == null)
            throwDie()
        else {
            currentROLE = savedInstanceState.getInt(PREVIOUS_ROLE)
            dieTextView.text = currentROLE.toString()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PREVIOUS_ROLE, currentROLE)
    }//save current die roll

    fun throwDie() {
        currentROLE = (Random.nextInt(dieSides)+1)
        dieTextView.text = currentROLE.toString()
    }
    private fun promptForDieSides() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Enter Number of Sides for the Die")
        // Set up the input
        val input = EditText(requireContext())
        builder.setView(input)
        // Set up the buttons
        builder.setPositiveButton("OK") { dialog, which ->
            val inputValue = input.text.toString()
            dieSides = inputValue.toIntOrNull() ?: 6 // Default to 6 if input is invalid
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
        builder.show()
    }
}