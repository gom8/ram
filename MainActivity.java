package net.gommette.ram;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    private EditText edtNotes;
    private TextView tvwTravail;
    private EditText edtTravail;
    private Button btnQuit;
    private Button btnHello;
    private boolean isHidden;
    private MenuItem macHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Notes.getPreferences(this);

        edtTravail = findViewById(R.id.edtTravail);
        tvwTravail = findViewById(R.id.tvwTravail);

        isHidden = Notes.getHidden(this);
        hide(isHidden, edtTravail);

        btnQuit = findViewById(R.id.btnQuit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
        btnHello = findViewById(R.id.btnHello);
        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "❤️", Toast.LENGTH_SHORT).show();
            }
        });

        edtNotes = findViewById(R.id.edtTravail);
        edtNotes.setText(Notes.getNotes(this));

        // ça, ça fait que chaque changement est enregistré
        edtNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                Notes.setNotes(s.toString());
            }
        });

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void hide(boolean hide, EditText TextElt){
        if (hide) {
            int start = TextElt.getSelectionStart();
            int end = TextElt.getSelectionEnd();
            TextElt.setTransformationMethod(new PasswordTransformationMethod());
            TextElt.setSelection(start, end);
        } else {
            int start = TextElt.getSelectionStart();
            int end = TextElt.getSelectionEnd();
            TextElt.setTransformationMethod(null);
            TextElt.setSelection(start, end);
        }
    }

    protected void hideswitch(){
        if (!isHidden) {
            macHide.setIcon(R.drawable.my_ic_visibility_off);
        } else {
            macHide.setIcon(R.drawable.my_ic_visibility);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        macHide = menu.findItem(R.id.mac_hide);
        hideswitch();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mac_hide:
                isHidden = !isHidden;
                Notes.setHidden(isHidden);
                hideswitch();
                hide(isHidden, edtTravail);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}