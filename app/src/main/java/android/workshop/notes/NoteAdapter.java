package android.workshop.notes;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private static final String NOTES_STATE = "state";

    private ArrayList<Note> notes;

    public NoteAdapter() {
        notes = new ArrayList<>();
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(NOTES_STATE, notes);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        notes = savedInstanceState.getParcelableArrayList(NOTES_STATE);
        if (notes == null) {
            notes = new ArrayList<>();
        }
    }

    public void addNote(Note note) {
        notes.add(note);
        notifyItemInserted(notes.size());
    }

    public void deleteAll() {
        notes.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView titleTextView;
        private TextView contentTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.deleteButton).setOnClickListener(this);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            contentTextView = (TextView) itemView.findViewById(R.id.contentTextView);
        }

        public void bind(Note note) {
            titleTextView.setText(note.getTitle());
            contentTextView.setText(note.getContent());
        }

        @Override
        public void onClick(View v) {
            // Delete was clicked
            int position = getAdapterPosition();
            notes.remove(position);
            notifyItemRemoved(position);
        }
    }
}
