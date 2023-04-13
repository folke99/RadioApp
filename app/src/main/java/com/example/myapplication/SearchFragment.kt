package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.viewmodel.SearchViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private lateinit var searchViewModel: SearchViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        // Connect view model
        searchViewModel = ViewModelProvider(this)[searchViewModel::class.java]

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}