package uz.abbosbek.crud_task_5.screens.userScreen.userSingleScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import uz.abbosbek.crud_task_5.database.AppDatabase
import uz.abbosbek.crud_task_5.databinding.FragmentSingleUserBinding
import uz.abbosbek.crud_task_5.reopsitory.UserRepository

class SingleUserFragment : Fragment() {
    private val binding by lazy { FragmentSingleUserBinding.inflate(layoutInflater) }
    private lateinit var viewModel: UserSingleFragmentViewModel
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

        val userRepository = UserRepository(AppDatabase.getInstance(requireContext()))
        val singleUserViewModelFactory = UserSingleFragmentViewModelFactory(userRepository)

        viewModel = ViewModelProvider(
            requireActivity(),
            singleUserViewModelFactory
        )[UserSingleFragmentViewModel::class.java]

        viewModel.getUserById(userId!!)

        viewModel.myResponse.observe(viewLifecycleOwner) { response ->
            binding.apply {
                userName.text = "${response?.first_name} ${response?.last_name}"
                userEmail.text = response?.email
                Picasso.get().load(response?.avatar).into(userImage)

            }
        }
    }
}