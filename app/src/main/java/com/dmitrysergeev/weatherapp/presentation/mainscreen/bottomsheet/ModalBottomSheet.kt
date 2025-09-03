package com.dmitrysergeev.weatherapp.presentation.mainscreen.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmitrysergeev.weatherapp.databinding.BottomSheetContentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet(
    private val title: String,
    private val contentLayoutId: Int
) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetContentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetContentBinding.inflate(layoutInflater, container, false)
        val contentView = inflater.inflate(contentLayoutId, binding.contentContainer, false)
        binding.contentContainer.addView(contentView)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailsTitle.text = title

        binding.closeButton.setOnClickListener{
            this.dismiss()
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}