package com.example.sars2.a21v.RecycleView;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sars2.a21v.Person;
import com.example.sars2.a21v.R;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder  {

        private TextView mPersonName;
        private TextView mPersonAge;
        private CardView mCardView;


        PersonViewHolder(View itemView) {
            super(itemView);
            mPersonName = (TextView)itemView.findViewById(R.id.person_name);
            mPersonAge = (TextView)itemView.findViewById(R.id.person_age);
            mCardView = (CardView)itemView.findViewById(R.id.cv);

        }

    }

    List<Person> mPersons;

    public RVAdapter(List<Person> persons){
        this.mPersons = persons;
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

    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.mPersonName.setText(mPersons.get(i).getName());
        personViewHolder.mPersonAge.setText(mPersons.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }
}
