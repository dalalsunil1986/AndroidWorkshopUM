package android.workshop.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        // Setup menu
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);

        // Setup fab click
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchAddNote();
            }
        });

        // Setup RecyclerView
        noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        noteAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        noteAdapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            noteAdapter.deleteAll();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NoteActivity.REQUEST_ADD && resultCode == RESULT_OK && data != null) {
            noteAdapter.addNote((Note) data.getParcelableExtra(NoteActivity.RESULT_NOTE));
        }
    }

    private void launchAddNote() {
        Intent intent = new Intent(this, NoteActivity.class);
        startActivityForResult(intent, NoteActivity.REQUEST_ADD);
    }

    private void bindViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }
}
