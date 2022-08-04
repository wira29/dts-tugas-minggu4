package com.example.dts_tugas_minggu4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recylerAdapter: Adapter

    private var list = mutableListOf<String>()

    private var correctList = mutableListOf<String>("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", " ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.add("A")
        list.add("B")
        list.add("C")
        list.add("D")
        list.add("E")
        list.add("F")
        list.add("G")
        list.add("H")
        list.add("I")
        list.add("J")
        list.add("K")
        list.add("L")
        list.add("M")
        list.add("N")
        list.add("O")
        list.add(" ")

        list.shuffle()

        recyclerView = findViewById(R.id.recyclerview)
        recylerAdapter = Adapter(list.toTypedArray())

        recyclerView.adapter = recylerAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 4)

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private var simpleCallback = object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT, 0) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            var startPosition = viewHolder.adapterPosition
            var endPosition = target.adapterPosition

            var tmp = list[endPosition]
            list[endPosition] = list[startPosition]
            list[startPosition] = tmp

//            Collections.swap(list, startPosition, endPosition)
            recyclerView.adapter?.notifyItemMoved(startPosition, endPosition)
            recylerAdapter.listUpdate(list.toTypedArray())

            check()
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            TODO("Not yet implemented")
        }

    }

    inline fun<reified T> isEqual(first: List<T>, second: List<T>): Boolean {

        if (first.size != second.size) {
            return false
        }

        return first.toTypedArray() contentEquals second.toTypedArray()
    }

    private fun check() {
        if(isEqual(list, correctList)){
            Log.d("check", "berhasil !!")
            Toast.makeText(this, "You win it !", Toast.LENGTH_LONG).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ulangi -> {
                list.shuffle()
                recylerAdapter.listUpdate(list.toTypedArray())
            }
            R.id.keluar -> finish()
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu, menu)
        return true
    }
}