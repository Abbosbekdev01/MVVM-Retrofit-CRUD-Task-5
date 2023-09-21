package uz.abbosbek.crud_task_5.screens.resourcesScreen.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.abbosbek.crud_task_5.database.entity.resource.ResourceEntity
import uz.abbosbek.crud_task_5.databinding.ResourceItemBinding

class ResourceAdapter(var list: ArrayList<ResourceEntity> = ArrayList(), val rvClick: RvClick) :
    RecyclerView.Adapter<ResourceAdapter.Vh>() {

    inner class Vh(private val resourceItemBinding: ResourceItemBinding) :
        RecyclerView.ViewHolder(resourceItemBinding.root) {

        fun onBind(resourceResponse: ResourceEntity) {
            resourceItemBinding.itemYear.text = resourceResponse.year.toString().trim()
            resourceItemBinding.itemFirstname.text = resourceResponse.name.trim()
            resourceItemBinding.itemValue.text = resourceResponse.pantone_value.trim()
            resourceItemBinding.root.setCardBackgroundColor(Color.parseColor(resourceResponse.color))

            resourceItemBinding.popupMenu.setOnClickListener {
                rvClick.menuClick(resourceItemBinding.popupMenu, resourceResponse)
            }

            resourceItemBinding.root.setOnClickListener {
                rvClick.itemClick(resourceResponse)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ResourceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList: List<ResourceEntity>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    interface RvClick {
        fun menuClick(imageView: ImageView, resourceResponse: ResourceEntity)

        fun itemClick(resourceResponse: ResourceEntity)
    }
}