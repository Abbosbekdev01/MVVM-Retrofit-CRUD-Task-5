package uz.abbosbek.crud_task_5.screens.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.abbosbek.crud_task_5.R
import uz.abbosbek.crud_task_5.databinding.FragmentMainBinding
import uz.abbosbek.crud_task_5.utils.Constants
import uz.abbosbek.crud_task_5.utils.LocalStorage

class MainFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnResource.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_resourceFragment)
            }
            btnUsers.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_userFragment)
            }
        }

        binding.logOut.setOnClickListener {
            LocalStorage.removeData(key = Constants.KEY_TOKEN)
            findNavController().navigate(R.id.action_mainFragment_to_registerFragment)
        }
    }
}