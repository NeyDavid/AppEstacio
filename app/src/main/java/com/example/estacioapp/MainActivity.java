package com.example.estacioapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;


    private TextInputLayout textInputLayoutLogin;
    private TextInputLayout textInputLayoutSenha;
    private Button buttonLogar;
    private TextView textViewCadastreSe;
    private Intent intentIrTelaMenuInicial;
    private Intent intentIrTelaCadastro;
    private ProgressBar progressBarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autenticacao = FirebaseAuth.getInstance();

       textInputLayoutLogin = findViewById(R.id.textFieldLogin);
       textInputLayoutSenha = findViewById(R.id.TextInputLayoutSenha);
       textViewCadastreSe = findViewById(R.id.textViewCadastreSe);
       progressBarLogin = findViewById(R.id.progressBarLogin);

       buttonLogar = findViewById(R.id.buttonLogar);

       intentIrTelaCadastro = new Intent(MainActivity.this, CadastroActivity.class);
       intentIrTelaMenuInicial = new Intent(MainActivity.this, MenuInicialActivity.class);

       textViewCadastreSe.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                startActivity(intentIrTelaCadastro);
           }
       });

       buttonLogar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               buttonLogar.setEnabled(false);
               buttonLogar.setText("Logando...");
               progressBarLogin.setVisibility(View.VISIBLE);
               String email = textInputLayoutLogin.getEditText().getText().toString();
               String senha = textInputLayoutSenha.getEditText().getText().toString();

               logar(email,senha);
               progressBarLogin.setVisibility(View.GONE);
               buttonLogar.setText("Logar");
               buttonLogar.setEnabled(true);

           }
       });

    }

    public void logar (String email, String senha){


        autenticacao.signInWithEmailAndPassword(email,senha).
                addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            textInputLayoutSenha.setError("");
                            startActivity(intentIrTelaMenuInicial);
                    } else {
                            textInputLayoutSenha.setError("Login ou Senha inv??lido");
                            progressBarLogin.setVisibility(View.GONE);
                        }
                }
    });

    }
}