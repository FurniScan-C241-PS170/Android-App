package com.dicoding.furniscan.ui.result

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.furniscan.R
import com.dicoding.furniscan.databinding.ActivityResultBinding
import com.dicoding.furniscan.ui.scan.ScanActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class ResultActivity : AppCompatActivity() {


    private var currentImageUri: Uri? = null

    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUriString = intent.getStringExtra(ScanActivity.EXTRA_CAMERAX_IMAGE)
        currentImageUri = Uri.parse(imageUriString)

        binding.ivPreview.setImageURI(currentImageUri)

        showBottomSheetDialog()

    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_bottom, null)

        val displayMetrics = resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels

        val layoutParams = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.MATCH_PARENT,
            screenHeight
        )
        view.layoutParams = layoutParams

        bottomSheetDialog.setContentView(view)

        val bottomSheetBehavior = BottomSheetBehavior.from(view.parent as View)
        bottomSheetBehavior.isFitToContents = false
        bottomSheetBehavior.setExpandedOffset(0)
        bottomSheetDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bottomSheetDialog.show()
    }
}