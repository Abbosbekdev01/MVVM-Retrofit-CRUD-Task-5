package uz.abbosbek.crud_task_5.screens.resourcesScreen.resourceSingleScreen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.abbosbek.crud_task_5.database.AppDatabase
import uz.abbosbek.crud_task_5.databinding.FragmentResourceSingleBinding
import uz.abbosbek.crud_task_5.reopsitory.ResourceRepository

private const val TAG = "ResourceSingleFragment"

class ResourceSingleFragment : Fragment() {
    private val binding by lazy { FragmentResourceSingleBinding.inflate(layoutInflater) }
    private lateinit var viewModel: ResourceSingleFragmentViewModel
    private var userId: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userId = arguments?.getInt("id")

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        Log.d(TAG, "onViewCreated: userId: $userId")
        val resourceRepository = ResourceRepository(AppDatabase.getInstance(requireContext()))
        val singleViewModelFactory = ResourceSingleFragmentViewModelFactory(resourceRepository)

        viewModel = ViewModelProvider(
            requireActivity(),
            singleViewModelFactory
        )[ResourceSingleFragmentViewModel::class.java]

        viewModel.getResourceById(userId!!)

        viewModel.postSingleResource.observe(viewLifecycleOwner) { response ->
            binding.firstName.text = response?.name
            Log.d(TAG, "onViewCreated: resource: $response")
            binding.value.text = response?.pantone_value
            binding.year.text = response?.year.toString()
            binding.root.setBackgroundColor(Color.parseColor(response?.color))
        }


    }
}