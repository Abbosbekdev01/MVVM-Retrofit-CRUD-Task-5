package uz.abbosbek.crud_task_5.screens.registerScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.abbosbek.crud_task_5.R
import uz.abbosbek.crud_task_5.databinding.FragmentRegisterBinding
import uz.abbosbek.crud_task_5.models.requests.UserRegisterRequest
import uz.abbosbek.crud_task_5.reopsitory.RegisterRepository
import uz.abbosbek.crud_task_5.utils.Constants
import uz.abbosbek.crud_task_5.utils.LocalStorage

private const val TAG = "Main"

class RegisterFragment : Fragment() {
    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }
    private lateinit var viewModel: RegisterFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val registerRepository = RegisterRepository()
        val viewModelFactory = RegisterFragmentViewModelFactory(registerRepository)

        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[RegisterFragmentViewModel::class.java]

        viewModel.myResponseRegister.observe(viewLifecycleOwner) { response ->
            Log.d(TAG, "onViewCreated: ${response.token}")
            LocalStorage.putString(key = Constants.KEY_TOKEN, value = response.token)
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.emailItem.text.toString().trim()
            val password = binding.passwordItem.text.toString().trim()

            if (email.isNotEmpty()) {
                val userRegister = UserRegisterRequest(email = email, password = password)
                viewModel.getRegister(userRegister)
            } else {
                binding.emailItem.error = "Email is Empty"
            }
        }

    }
}