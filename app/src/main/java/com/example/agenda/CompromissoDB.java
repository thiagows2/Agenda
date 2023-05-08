package com.example.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CompromissoDB {
    private final SQLiteDatabase mDatabase;

    public CompromissoDB(Context contexto) {
        mDatabase = new CompromissosDBHelper(contexto).getWritableDatabase();
    }

    private static ContentValues getValoresConteudo(Compromisso c) {
        ContentValues valores = new ContentValues();

        valores.put(CompromissosDBSchema.CompromissosTbl.Cols.DATA, c.getData());
        valores.put(CompromissosDBSchema.CompromissosTbl.Cols.HORA, c.getHora());
        valores.put(CompromissosDBSchema.CompromissosTbl.Cols.DESCRICAO, c.getDescricao());
        return valores;
    }
    public void addCompromisso(Compromisso r) {
        ContentValues valores = getValoresConteudo(r);
        mDatabase.insert(CompromissosDBSchema.CompromissosTbl.NOME, null, valores);
    }

    void removeBanco() {
        int delete;
        delete = mDatabase.delete(
                CompromissosDBSchema.CompromissosTbl.NOME, null, null
        );
    }

    public Cursor queryCompromissos(String clausulaWhere, String[] argsWhere) {
        return mDatabase.query(CompromissosDBSchema.CompromissosTbl.NOME,
                null,
                clausulaWhere,
                argsWhere,
                null,
                null,
                null
        );
    }

}
