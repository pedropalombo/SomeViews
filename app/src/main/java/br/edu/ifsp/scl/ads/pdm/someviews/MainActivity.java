package br.edu.ifsp.scl.ads.pdm.someviews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Objetos de binding com as views
    private EditText nomeEt;
    private EditText sobreNomeEt;
    private Spinner estadoCivilSp;
    private LinearLayout conjugeLl;
    private EditText nomeConjugeEt;
    private EditText sobreNomeConjugeEt;
    private EditText emailEt;
    private RadioGroup sexoRg;
    private RadioButton masculinoRb;

    private String nomeCompleto;


    @Override
    //constructor
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binging com as views (ligação)
        bindViews();

        //element.setonitemclick(new onitemclick) => triggers method on click
        estadoCivilSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int posicao, long id) {
                TextView viewSelecionada = (TextView) view; //converting view to TextView

                //checks if the selected was "Casado", and set the Layout to 'Visible' (before: gone)
                if(viewSelecionada.getText().equals("Casado")){
                    conjugeLl.setVisibility(View.VISIBLE);

                } else { //otherwise, it sets the Layout to 'Gone' and empties the textfields
                    conjugeLl.setVisibility(View.GONE);
                    nomeConjugeEt.setText("");
                    sobreNomeConjugeEt.setText("");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //element.addtextchanged(new TextWatcher) => manipulates strings
        sobreNomeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nomeCompleto = nomeEt.getText().toString() + " " + charSequence; //concats 'nomeEt' and 'sobreNomeEt' (indicated by charSequence)
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    //binds vars to their respective IDs |=> slow af way
    private void bindViews(){
        nomeEt = findViewById(R.id.nomeEt);
        sobreNomeEt = findViewById(R.id.sobreNomeEt);
        emailEt = findViewById(R.id.emailEt);
        estadoCivilSp = findViewById(R.id.estadoCivilSp);
        conjugeLl = findViewById(R.id.conjugeLl);
        nomeConjugeEt = findViewById(R.id.nomeConjugeEt);
        sobreNomeConjugeEt = findViewById(R.id.sobreNomeConjugeEt);
        sexoRg = findViewById(R.id.sexoRg);
        masculinoRb = findViewById(R.id.masculinoRb);
    }

    //"saves" info
    public void save(View botao){
        //alerts
        Toast.makeText(this, "clicou no salvar", Toast.LENGTH_SHORT).show(); //Toast => basically alert
    }

    //turns form into default
    public void clear(View botao){
        //top editTexts
        nomeEt.setText("");
        sobreNomeEt.setText("");
        emailEt.setText("");

        //setting Spinner item shown
        //estadoCivilSp.setPrompt("Solteiro");
        estadoCivilSp.setSelection(0);

        //hidding Layout visibility and removing the editTexts infos
        conjugeLl.setVisibility(View.GONE);
        nomeConjugeEt.setText("");
        sobreNomeConjugeEt.setText("");

        //clears radioButtons selections and sets masculinoRb as active
        sexoRg.clearCheck();
        masculinoRb.toggle();

        //alerts
        Toast.makeText(this, "clicou no limpar", Toast.LENGTH_SHORT).show();
    }

    //sacola | saves Instance States data in-between changes of state (eg: onPause() -> onRestart()) |-> always triggers after onStop()
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}