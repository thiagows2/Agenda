package com.example.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RespostaDB {
    private Context mContext;
    private static Context mStaticContext;
    private SQLiteDatabase mDatabase;

    public RespostaDB(Context contexto){
        mContext = contexto.getApplicationContext();
        mStaticContext = mContext;
        mDatabase = new RespostasDBHelper(mContext).getWritableDatabase();
    }
    private static ContentValues getValoresConteudo(Resposta r){
        ContentValues valores = new ContentValues();

        valores.put(RespostasDBSchema.RespostasTbl.Cols.UUID, r.getmId().toString());
        valores.put(RespostasDBSchema.RespostasTbl.Cols.RESPOSTA_OFERECIDA, r.mRespostaOferecida());
        valores.put(RespostasDBSchema.RespostasTbl.Cols.RESPOSTA_CORRETA, r.ismRespostaCorreta());
        valores.put(RespostasDBSchema.RespostasTbl.Cols.COLOU, r.mColou());
        return valores;
    }
    public void addResposta(Resposta r){
        ContentValues valores = getValoresConteudo(r);
        mDatabase.insert(RespostasDBSchema.RespostasTbl.NOME, null, valores);
    }

    void removeBanco(){
        int delete;
        delete = mDatabase.delete(
                RespostasDBSchema.RespostasTbl.NOME, null, null
        );
    }

    public Cursor queryRespostas(String clausulaWhere, String[] argsWhere) {
        return mDatabase.query(RespostasDBSchema.RespostasTbl.NOME,
                null,
                clausulaWhere,
                argsWhere,
                null,
                null,
                null
        );
    }

}
