package uz.abbosbek.crud_task_5.screens.resourcesScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.abbosbek.crud_task_5.R
import uz.abbosbek.crud_task_5.database.AppDatabase
import uz.abbosbek.crud_task_5.database.entity.resource.ResourceEntity
import uz.abbosbek.crud_task_5.databinding.FragmentResourceBinding
import uz.abbosbek.crud_task_5.reopsitory.ResourceRepository
import uz.abbosbek.crud_task_5.screens.resourcesScreen.adapters.ResourceAdapter

private const val TAG = "ResourceFragment"

class ResourceFragment : Fragment(), ResourceAdapter.RvClick {

    private val binding by lazy { FragmentResourceBinding.inflate(layoutInflater) }
    private lateinit var viewModel: ResourceFragmentViewModel
    private val myAdapter by lazy { ResourceAdapter(rvClick = this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }


        viewModel = ViewModelProvider(
            requireActivity(),
            ResourceFragmentViewModelFactory(
                ResourceRepository(
                    AppDatabase.getInstance(
                        requireContext()
                    )
                )
            )
        )[ResourceFragmentViewModel::class.java]


        viewModel.getResources()
        viewModel.myResponse.observe(viewLifecycleOwner){ response->
            // TODO: Api dan kelgan ma'lumotlarni recyclerView ga chiqaryabmiz
            myAdapter.setData(response)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner)
        { errorResponse ->
            Toast.makeText(requireContext(), errorResponse, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        binding.recycler.adapter = myAdapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun menuClick(imageView: ImageView, resourceResponse: ResourceEntity) {
        val popupMenu = PopupMenu(requireContext(), imageView)
        popupMenu.inflate(R.menu.popup_menu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.edit_user -> {
                    Toast.makeText(requireContext(), "Edit in not Fanction", Toast.LENGTH_SHORT)
                        .show()
                }

                R.id.delete_user -> {
                    Toast.makeText(requireContext(), "Delete is not Fanction", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            true
        }
        popupMenu.show()
    }

    override fun itemClick(resourceResponse: ResourceEntity) {
        findNavController().navigate(
            R.id.action_resourceFragment_to_resourceSingleFragment,
            bundleOf("id" to resourceResponse.id)
        )
    }
}