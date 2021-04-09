package com.swein.simplesidemenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


class SideMenuFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var textViewOne: TextView
    private lateinit var textViewTwo: TextView
    private lateinit var textViewThree: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_side_menu, container, false)
        findView()
        setListener()
        return rootView
    }

    private fun findView() {
        textViewOne = rootView.findViewById(R.id.textViewOne)
        textViewTwo = rootView.findViewById(R.id.textViewTwo)
        textViewThree = rootView.findViewById(R.id.textViewThree)
    }

    private fun setListener() {
        textViewOne.setOnClickListener {
            Toast.makeText(context, "click side menu 1", Toast.LENGTH_SHORT).show()
        }

        textViewTwo.setOnClickListener {
            Toast.makeText(context, "click side menu 2", Toast.LENGTH_SHORT).show()
        }

        textViewThree.setOnClickListener {
            Toast.makeText(context, "click side menu 3", Toast.LENGTH_SHORT).show()
        }
    }
}