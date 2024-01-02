package com.example.todolistmain;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.todolistmain.databinding.FragmentTodoListBinding;
import com.example.todolistmain.databinding.ListRowItemBinding;

import java.util.ArrayList;

public class TodoListFragment extends Fragment {

    ArrayList<Items> itemsList = new ArrayList<Items>();
    FragmentTodoListBinding listBinding;

    private ListAdapter itemListAdapter;
    RecyclerView recyclerView;


    public TodoListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listBinding = FragmentTodoListBinding.inflate(inflater,container,false);
        return listBinding.getRoot();
        }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("To Do List App");
        Log.d("Nishant", "onViewCreated: "+itemsList.size());


        recyclerView = listBinding.recylerView;
        itemListAdapter = new ListAdapter(itemsList, getContext());
        recyclerView.setAdapter(itemListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        listBinding.addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = listBinding.editList.getText().toString();
                if(itemName.equals("") || itemName.length()==0){
                    Toast.makeText(getActivity(), "Enter a item", Toast.LENGTH_SHORT).show();
                }else{
                    Items items = new Items(itemName);
                    itemListener.AddItem(items);
                }
            }
        });

        listBinding.addItemClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemListener.clearItem();
                itemsList.clear();
                itemListAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setItemsList(Items items){
        Log.d("Capital data", "Data: "+items);
        itemsList.add(items);
        itemListAdapter.notifyDataSetChanged();
    }

    TodoListFragmentListener itemListener;
    interface TodoListFragmentListener {
        void AddItem(Items item);
        void clearItem();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        itemListener = (TodoListFragmentListener) context;
    }

    class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{
        Context context;
        ArrayList<Items> item;

        public ListAdapter(ArrayList<Items> itemsList, Context context) {
            Log.d("Nishant ListAdapter", "ListAdapter: called ");
            this.context = context;
            this.item = itemsList;
        }


        @NonNull
        @Override
        public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("Nishant onCreateViewHolder", "onCreateViewHolder: called ");

            ListRowItemBinding binding = ListRowItemBinding.inflate(getLayoutInflater(),parent,false);
            return new ListViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
            Log.d("Nishant onBindViewHolder", "onBindViewHolder: called ");

            Items listItem = item.get(position);
            holder.setUpUI(listItem);
        }

        @Override
        public int getItemCount() {
            return item.size();
        }

        class ListViewHolder extends RecyclerView.ViewHolder{

            ListRowItemBinding mbinding;
            public ListViewHolder(ListRowItemBinding binding) {
                super(binding.getRoot());
                Log.d("Nishant ListViewHolder", "ListViewHolder: ");

                mbinding = binding;
            }

            public void setUpUI(Items listItem){
                Log.d("Nishant setupUI", "setUpUI: "+listItem.toString());
            mbinding.textViewItemAdded.setText(String.valueOf(listItem.getItemName()));
            mbinding.checkBoxItemCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isChecked = ((CheckBox) view).isChecked();
                    if(isChecked){
                        item.get(getAdapterPosition()).setSelected(true);
                        mbinding.textViewItemAdded.setTextColor(Color.BLACK);
                        mbinding.textViewItemAdded.setBackgroundColor(Color.GREEN);

                    }else{
                        item.get(getAdapterPosition()).setSelected(false);
                        mbinding.textViewItemAdded.setTextColor(Color.BLACK);
                        mbinding.textViewItemAdded.setBackgroundColor(Color.WHITE);
                    }
                }
            });
            }
        }
    }
}