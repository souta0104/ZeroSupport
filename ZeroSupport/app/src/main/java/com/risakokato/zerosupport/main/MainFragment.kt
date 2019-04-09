package com.risakokato.zerosupport.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.risakokato.zerosupport.R
import com.risakokato.zerosupport.model.entity.BelongingsRoom
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainFragment : Fragment(), MainContract.View, MainAdapter.OnCheckedChangeListener {
    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Default)

    private val presenter: MainContract.Presenter by inject<MainPresenter> { parametersOf(this, scope) }

    lateinit var adapter: MainAdapter

    private var mList: MutableList<BelongingsRoom> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        findNavController().graph.label = "MainFragment"
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MainAdapter(mList, context!!)
        adapter.listener = this
        main_list_view.adapter = adapter
        main_list_view.layoutManager = LinearLayoutManager(context!!)

    }

    override fun updateList(list: List<BelongingsRoom>) {
        mList = list.toMutableList()
        adapter.setList(mList)
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.setTodayList()
    }

    override fun onChecked(buttonView: View, isChecked: Boolean) {
        println("clicked")
    }

    override fun onDestroy() {
        super.onDestroy()

        job.cancel()
    }
}