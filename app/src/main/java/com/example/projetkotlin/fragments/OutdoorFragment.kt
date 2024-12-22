package com.example.projetkotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projetkotlin.R

class OutdoorFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Lier le layout du fragment
        return inflater.inflate(R.layout.fragment_outdoor, container, false)
    }
}
