package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CompromissoDB mCompromissoDB;
    private String dataSelecionada;
    private String horaSelecionada;
    private EditText mDescricao;

    void setDataSelecionada(String dataSelecionada) {
        this.dataSelecionada = dataSelecionada;
    }

    void setHoraSelecionada(String horaSelecionada) {
        this.horaSelecionada = horaSelecionada;
    }

    private TextView mCompromissosTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDescricao = findViewById(R.id.descricao_input);
        Button botaoCriar = findViewById(R.id.btn_criar);

        botaoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descricao = mDescricao.getText().toString();
                criaCompromisso(dataSelecionada, horaSelecionada, descricao);
            }
        });

        Button botaoHoje = findViewById(R.id.btn_hoje);
        mCompromissosTextView = findViewById(R.id.textView_scroll);

        botaoHoje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostraCompromissos();
            }
        });
    }

    public void mostraDialogoTimePicker(View v) {
        DialogFragment newFragment = new FragmentoTimePicker();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void mostraDialogoDatePicker(View v) {
        DialogFragment newFragment = new FragmentoDatePicker();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void criaCompromisso(String data, String hora, String descricao) {
        if (mCompromissoDB == null) {
            mCompromissoDB = new CompromissoDB(this);
        }

        Compromisso compromisso = new Compromisso(data, hora, descricao);
        mCompromissoDB.addCompromisso(compromisso);
    }

    private void mostraCompromissos() {
        if (mCompromissoDB == null) return;

        Cursor cursor = mCompromissoDB.queryCompromissos(null, null);
        StringBuilder stringBuilder = new StringBuilder();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String data = cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.CompromissosTbl.Cols.DATA));
                String hora = cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.CompromissosTbl.Cols.HORA));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(CompromissosDBSchema.CompromissosTbl.Cols.DESCRICAO));

                if (hora != null && !descricao.isEmpty()) {
                    stringBuilder.append(data).append(" - ").append(hora).append(" - ").append(descricao).append("\n");
                }
            } while (cursor.moveToNext());

            cursor.close();
        }

        mCompromissosTextView.setText(stringBuilder.toString());
    }

}
