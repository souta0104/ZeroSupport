package com.risakokato.zerosupport.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.risakokato.zerosupport.R
import com.risakokato.zerosupport.ext.IntentType
import com.risakokato.zerosupport.model.entity.BelongingsRoom
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ListFragment : Fragment(), ListContract.View, BelongingsAdapter.OnClickListener {
    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Default)

    private val presenter: ListContract.Presenter by inject<ListPresenter> { parametersOf(this, scope) }

    lateinit var adapter: BelongingsAdapter

    private var mList: MutableList<BelongingsRoom> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        findNavController().graph.label = "ListFragment"
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = BelongingsAdapter(context, mList)
        adapter.listener = this
        list_view.adapter = adapter
        list_view.layoutManager = LinearLayoutManager(context!!)

        fab.setOnClickListener {
            intent(IntentType.FRAGMENT_NEW_CONTENT)
        }
    }

    override fun updateList(list: List<BelongingsRoom>) {
        mList = list.toMutableList()
        adapter.setList(mList)
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.setList()
    }

    override fun setOnLongClickListener(position: Int) {
        Dialog {
            presenter.deleteItem(mList[position])
            Snackbar.make(view!!, "delete success", Snackbar.LENGTH_SHORT)
            mList.removeAt(position)
            updateList(mList)
        }.show(fragmentManager!!,"ts")

    }

    override fun intent(intentType: IntentType) {
        when (intentType) {
            IntentType.FRAGMENT_NEW_CONTENT -> findNavController().navigate(R.id.newContentFragment)
        }
    }

    class Dialog(private val ok: () -> Unit) : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
            super.onCreateDialog(savedInstanceState)
            return AlertDialog.Builder(context!!)
                    .setMessage(getString(R.string.dialog_message_list_fragment))
                    .setPositiveButton("OK") { _, _ -> ok() }.setNegativeButton("No", null).create()
        }
    }
}