package com.example.orders.presentation.orders

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.elveum.elementadapter.ElementListAdapter
import com.elveum.elementadapter.simpleAdapter
import com.example.orders.R
import com.example.orders.databinding.FragmentOrderListBinding
import com.example.orders.databinding.ItemOrderBinding
import com.example.orders.databinding.ItemOrderProductBinding
import com.example.orders.domain.entities.OrderItem
import com.example.orders.domain.entities.OrderStatus
import com.example.orders.presentation.orders.entites.UiOrder
import com.example.presentation.viewBinding
import com.example.presentation.views.observe
import com.example.presentation.views.setupSimpleList
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OrdersListFragment @Inject constructor()  : Fragment(R.layout.fragment_order_list)  {

    private val viewModel by viewModels<OrdersListViewModel>()

    private val binding by viewBinding<FragmentOrderListBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            val adapter = setupList()
            observeState(adapter)
            setupListeners()
        }
    }

    private fun FragmentOrderListBinding.observeState(adapter: ElementListAdapter<UiOrder>){
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue){ state ->
            adapter.submitList(state.orders)
        }
    }

    private fun FragmentOrderListBinding.setupList(): ElementListAdapter<UiOrder>{
        val adapter = createdOrdersAdapter()
        ordersRecycleView.adapter = adapter
        ordersRecycleView.setupSimpleList()
        return adapter
    }

    private fun FragmentOrderListBinding.setupListeners(){
        root.setTryAgainListener { viewModel.reload() }
    }


    private fun makeCreatedAtText(order: UiOrder): CharSequence{
        val text = getString(R.string.orders_created_at, order.createdAt)
        return HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
       // TODO()
    }

    private fun makeRecipientText(order: UiOrder): CharSequence {
        val text = getString(R.string.orders_recipient, order.recipient)
        return HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    private fun TextView.renderStatus(orderStatus: OrderStatus){
        text = orderStatus.name.lowercase().replaceFirstChar { it.titlecaseChar() }
        val color = when(orderStatus){
            OrderStatus.CREATED-> Color.rgb(0,128,192)
            OrderStatus.ACCEPTED->Color.rgb(192,128,32)
            OrderStatus.DELIVERING-> Color.rgb(64,32,192)
            OrderStatus.DONE -> Color.rgb(16,128,16)
            OrderStatus.CANCELLED-> Color.rgb(128,0,0)
        }
        setTextColor(color)
        val drawable = GradientDrawable()
        drawable.setColor(Color.argb(
            32, Color.red(color), Color.green(color), Color.blue(color)
        ))
        drawable.cornerRadius = resources.getDimensionPixelSize(R.dimen.status_radius).toFloat()
        background = drawable
    }

    private fun RecyclerView.makeOrderItemList(order: UiOrder){
        val adapter = this.adapter as? ElementListAdapter<OrderItem> ?: let{
            setupSimpleList()
            val newAdapter = createdOrderItemAdapter()
            this.adapter = newAdapter
            newAdapter
        }
        adapter.submitList(order.orderItems)
    }




    private fun createdOrdersAdapter() = simpleAdapter<UiOrder, ItemOrderBinding> {
        areContentsSame = {oldItem, newItem ->  oldItem.uuid==newItem.uuid }
        bind { order->
            orderIdTextView.text = getString(R.string.orders_order_id, order.uuid).uppercase()
            createdAtTextView.text = makeCreatedAtText(order)
            recipientTextView.text = makeRecipientText(order)
            orderItemsRecyclerView.makeOrderItemList(order)
            orderStatusTextView.renderStatus(order.status)
            if(!order.canCancel){
                cancelTextView.visibility = View.GONE
            }else{
                if(order.cancelInProgress){
                    cancelTextView.visibility = View.INVISIBLE
                }else{
                    cancelTextView.visibility = View.VISIBLE
                }
            }
            cancelProgressBar.isVisible = order.cancelInProgress
        }
        listeners {
            cancelTextView.onClick { viewModel.cancelOrder(it) }
        }
    }


    private fun createdOrderItemAdapter() = simpleAdapter<OrderItem, ItemOrderProductBinding> {
        areItemsSame = {oldItem, newItem -> oldItem.id == newItem.id}
        bind { cartItem ->
            productNameTextView.text = getString(R.string.orders_item_text, cartItem.name, cartItem.quantity)
            priceTextView.text = cartItem.price.text
        }
    }


}