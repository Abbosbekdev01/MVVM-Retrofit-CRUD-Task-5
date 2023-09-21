package uz.abbosbek.crud_task_5.screens.splashScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.abbosbek.crud_task_5.R
import uz.abbosbek.crud_task_5.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private val binding by lazy { FragmentSplashBinding.inflate(layoutInflater) }
    private lateinit var viewModel: SplashFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SplashFragmentViewModel::class.java]

        viewModel.checkUser()

        viewModel.registerToken.observe(requireActivity()) { isHaveToken ->
            if (isHaveToken) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_registerFragment)
            }
        }
    }
}