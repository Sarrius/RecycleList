package com.example.sars2.a21v;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder  {


        private TextView mPersonName;
        private TextView mPersonAge;


        PersonViewHolder(View itemView) {
            super(itemView);

            mPersonName = (TextView)itemView.findViewById(R.id.person_name);
            mPersonAge = (TextView)itemView.findViewById(R.id.person_age);

        }


    }

    List<Person> persons;

    RVAdapter(List<Person> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
        //розібратись шо я тут зробив
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {

        personViewHolder.mPersonName.setText(persons.get(i).name);
        personViewHolder.mPersonAge.setText(persons.get(i).age);

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
