package uz.abbosbek.crud_task_5.screens.userScreen.userAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.abbosbek.crud_task_5.database.entity.user.UserEntity
import uz.abbosbek.crud_task_5.databinding.UserItemBinding


class UserAdapter(
    var list: ArrayList<UserEntity> = ArrayList(),
    val rvClickUser: RvClickUser
) :
    RecyclerView.Adapter<UserAdapter.Vh>() {

    inner class Vh(private val userItemBinding: UserItemBinding) :
        RecyclerView.ViewHolder(userItemBinding.root) {

        @SuppressLint("SetTextI18n")
        fun onBind(user: UserEntity) {

            userItemBinding.itemFirstname.text = "${user.first_name} ${ user.last_name} "
            userItemBinding.itemEmail.text = user.email
            Picasso.get().load(user.avatar).into(userItemBinding.userImage)

            userItemBinding.userPopupmenu.setOnClickListener {
                rvClickUser.menuClick(userItemBinding.userPopupmenu, user)
            }
            userItemBinding.root.setOnClickListener {
                rvClickUser.itemClick(user)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newUserList: List<UserEntity>) {
        list.clear()
        list.addAll(newUserList)
        notifyDataSetChanged()
    }

    interface RvClickUser {
        fun menuClick(imageView: ImageView, user: UserEntity)

        fun itemClick(user: UserEntity)
    }
}