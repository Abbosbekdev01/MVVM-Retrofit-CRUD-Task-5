package uz.abbosbek.crud_task_5.screens.loginScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.abbosbek.crud_task_5.R
import uz.abbosbek.crud_task_5.databinding.FragmentLogingBinding
import uz.abbosbek.crud_task_5.models.requests.UserLoginRequest
import uz.abbosbek.crud_task_5.reopsitory.LoginRepository
import uz.abbosbek.crud_task_5.utils.Constants
import uz.abbosbek.crud_task_5.utils.LocalStorage

private const val TAG = "Main_Login"

class LoginFragment : Fragment() {
    private val binding by lazy { FragmentLogingBinding.inflate(layoutInflater) }
    private lateinit var viewModel: LoginFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginRepository = LoginRepository()
        val viewModelFactory = LoginFragmentViewModelFactory(loginRepository)

        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[LoginFragmentViewModel::class.java]

        viewModel.myResponseLogin.observe(viewLifecycleOwner) { response ->
            Log.d(TAG, "onViewCreated: $response")
            LocalStorage.putString(key = Constants.KEY_TOKEN, value = response.token)
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.emailItem.text.toString().trim()
            val password = binding.passwordItem.text.toString().trim()

            if (email.isNotEmpty()) {
                val userLogin = UserLoginRequest(email = email, password = password)
                viewModel.getLogin(userLogin)
            } else {
                binding.emailItem.error = "Email is Empty"
            }
        }
    }
}