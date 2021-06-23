package com.example.estacioapp.utilities;

import android.text.Editable;

public class ValidaCampos {

    public static boolean NOME_COMPLETO (String nome) {
        if (nome.isEmpty()) {
            return false;
        }
        return true;
    }
}
