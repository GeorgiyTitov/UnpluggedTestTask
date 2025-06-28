package com.gt.search

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gt.search.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    private lateinit var binding: FragmentInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSearch.setOnClickListener {
            val searchQuery = binding.editTextText.text.toString()

            if (searchQuery.isNotEmpty()) {
                val intent = buildSearchIntent(searchQuery)
                if (checkDeviceForSecuredApps()) {
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.secured_apps_are_absent),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun checkDeviceForSecuredApps(): Boolean {
        val pm = requireActivity().packageManager
        val requiredPerm = BuildConfig.SECURE_SEARCH_PERMISSION
        val packages = pm?.getPackagesHoldingPermissions(
            arrayOf(requiredPerm),
            PackageManager.GET_PERMISSIONS
        )
        return !packages.isNullOrEmpty()
    }

    private fun buildSearchIntent(searchQuery: String): Intent {
        val intent = Intent().apply {
            action = BuildConfig.SECURE_SEARCH_PERMISSION
            putExtra(requireActivity().getString(com.gt.common.R.string.query_key), searchQuery)
        }
        return intent
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}