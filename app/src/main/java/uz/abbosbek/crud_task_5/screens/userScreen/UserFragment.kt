package uz.abbosbek.crud_task_5.screens.userScreen

import android.os.Bundle
import android.util.Log
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
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.abbosbek.crud_task_5.R
import uz.abbosbek.crud_task_5.database.AppDatabase
import uz.abbosbek.crud_task_5.database.entity.user.UserEntity
import uz.abbosbek.crud_task_5.databinding.AddBottomSheetDialogBinding
import uz.abbosbek.crud_task_5.databinding.EditBottomSheetDialogBinding
import uz.abbosbek.crud_task_5.databinding.FragmentUserBinding
import uz.abbosbek.crud_task_5.models.requests.PutRequest
import uz.abbosbek.crud_task_5.models.requests.UserRequest
import uz.abbosbek.crud_task_5.reopsitory.UserRepository
import uz.abbosbek.crud_task_5.screens.userScreen.userAdapters.UserAdapter
import kotlin.coroutines.CoroutineContext

private const val TAG = "UserFragment"

class UserFragment() : Fragment(), UserAdapter.RvClickUser, CoroutineScope {
    private val binding by lazy { FragmentUserBinding.inflate(layoutInflater) }
    private lateinit var viewModel: UserFragmentViewModel
    private val myAdapter by lazy { UserAdapter(rvClickUser = this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUserRecyclerView()


        viewModel = ViewModelProvider(
            requireActivity(),
            UserFragmentViewModelFactory(
                UserRepository(
                    AppDatabase.getInstance(
                        requireContext()
                    )
                )
            )
        )[UserFragmentViewModel::class.java]

        launch {
        }

        viewModel.getUsers()
        viewModel.myResponse.observe(viewLifecycleOwner) { response ->
            myAdapter.setData(response)
            Log.d(TAG, "onViewCreated: $response")
        }

        // TODO: add qilingan ma'lumotni ko'rsatish uchun
        viewModel.addResponse.observe(viewLifecycleOwner) { addResponse ->
            Toast.makeText(
                requireContext(),
                "First Name: ${addResponse.name} \n Job: ${addResponse.job} \nCreated: ${addResponse.createdAt}",
                Toast.LENGTH_LONG
            ).show()
        }
        // TODO: Ma'lumot o'zgartirilganini bilish uchun
        viewModel.editResponse.observe(viewLifecycleOwner) { editResponse ->
            Toast.makeText(
                requireContext(),
                "First Name: ${editResponse.name} \n Job: ${editResponse.job} \nUpdate: ${editResponse.updatedAt}",
                Toast.LENGTH_LONG
            ).show()

        }

        viewModel.deleteResponse.observe(viewLifecycleOwner) { deleteResponse ->
            Toast.makeText(requireContext(), deleteResponse, Toast.LENGTH_SHORT).show()
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { errorResponse ->
            Toast.makeText(requireContext(), errorResponse, Toast.LENGTH_SHORT).show()
        }

        binding.btnAdd.setOnClickListener {
            addDialogOpen()
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun addDialogOpen() {
        val addDialog = BottomSheetDialog(requireContext())
        val addBottomDialog = AddBottomSheetDialogBinding.inflate(layoutInflater)
        addDialog.setContentView(addBottomDialog.root)
        addDialog.create()

        addBottomDialog.apply {
            btnSave.setOnClickListener {
                val firstName = editName.text.toString().trim()
                val lastName = editLastName.text.toString().trim()

                val userRequset = UserRequest(
                    name = firstName,
                    job = lastName
                )
                if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                    viewModel.addUser(userRequset)
                    addDialog.dismiss()
                } else {
                    Toast.makeText(requireContext(), "Data is Empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
        addDialog.show()
    }

    private fun setupUserRecyclerView() {
        binding.recycler.adapter = myAdapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun menuClick(imageView: ImageView, user: UserEntity) {
        val popupMenu = PopupMenu(requireContext(), imageView)
        popupMenu.inflate(R.menu.popup_menu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.edit_user -> {
                    editUser(user)
                }

                R.id.delete_user -> {
                    viewModel.deleteUser(user.id)
                }
            }
            true
        }
        popupMenu.show()
    }

    override fun itemClick(user: UserEntity) {
        findNavController().navigate(
            R.id.action_userFragment_to_singleUserFragment,
            bundleOf("id" to user.id)
        )
    }

    private fun editUser(user: UserEntity) {
        val dialog = BottomSheetDialog(requireContext())
        val editDialog = EditBottomSheetDialogBinding.inflate(layoutInflater)
        dialog.setContentView(editDialog.root)
        dialog.create()

        editDialog.apply {
            editName.setText(user.first_name)
            editLastName.setText(user.last_name)

            btnClose.setOnClickListener {
                dialog.dismiss()
            }

            btnSave.setOnClickListener {
                val firstName = editName.text.toString().trim()
                val lastName = editLastName.text.toString().trim()

                if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                    val putUser = PutRequest(
                        name = user.first_name,
                        job = user.email
                    )
                    viewModel.editUser(id, putUser)
                    dialog.dismiss()
                } else {
                    Toast.makeText(requireContext(), "Data is Empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
        dialog.show()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
}