package com.example.agenda;

import java.util.UUID;

public class Resposta {
    private final UUID mId;
    private final String mRespostaOferecida;
    private final boolean mRespostaCorreta;
    private final boolean mColou;

    public Resposta(boolean mRespostaCorreta, String mRespostaOferecida, boolean mColou) {
        this.mRespostaCorreta = mRespostaCorreta;
        this.mRespostaOferecida = mRespostaOferecida;
        this.mColou = mColou;
        mId = UUID.randomUUID();
    }

    public UUID getmId() {
        return mId;
    }

    public String mRespostaOferecida() { return mRespostaOferecida; }


    public boolean ismRespostaCorreta() {
        return mRespostaCorreta;
    }

    public boolean mColou() { return mColou; }

}
