package android.workshop.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;


public class NoteActivity extends AppCompatActivity {

    public static final int REQUEST_ADD = 2;
    public static final String RESULT_NOTE = "result_note";

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private EditText titleEditText;
    private EditText contentEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        bindViews();

        // Finish on back click
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void bindViews() {
        contentEditText = (EditText) findViewById(R.id.contentEditText);
        titleEditText = (EditText) findViewById(R.id.titleEditText);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    private void save() {
        String title = titleEditText.getText().toString().trim();
        String content = contentEditText.getText().toString().trim();

        // Check if we have both title and content
        if (!content.isEmpty() && !title.isEmpty()) {
            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);
            Intent data = new Intent();
            data.putExtra(RESULT_NOTE, note);
            setResult(RESULT_OK, data);
            finish();
        } else {
            // Show error
            Snackbar.make(toolbar, R.string.add_note_missing_data, Snackbar.LENGTH_LONG).show();
        }
    }
}
