package com.kaique.atividade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    private TextView nome;
    private  TextView telefone;
    private Button botao;
    List<Pessoa> dados;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        nome= findViewById(R.id.idNome);
        telefone= findViewById(R.id.idTelefone);
        botao= findViewById(R.id.idBt);
        dados = new ArrayList<>();
        recyclerView = findViewById(R.id.idRR);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ler();
    }
    public void salvar(View view){
        Pessoa p = new Pessoa();

            p.setNome(nome.getText().toString());
            p.setTelefone(telefone.getText().toString());
            DatabaseReference pessoas = databaseReference.child("pessoas");
            pessoas.push().setValue(p);
            //Snackbar snackbar = Snackbar.make(view,"Salvo com sucesso", Snackbar.LENGTH_SHORT);
           // snackbar.setBackgroundTint(Color.BLUE);
           // snackbar.show();

    }
    public void ler(){
        DatabaseReference pessoas = databaseReference.child("pessoas");

        pessoas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.i("Firebase",snapshot.getValue().toString());
                for (DataSnapshot item:snapshot.getChildren()
                ) {
                    Pessoa pessoa = item.getValue(Pessoa.class);
                    dados.add(pessoa);
                    Log.i("Firebase",pessoa.getNome());
                }
                recyclerView.setAdapter(new MeuAdapter(dados));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("Firebase",error.toString());
                Toast.makeText(getApplicationContext(),"erro de conexão",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void limpaCampos(){
        nome.setText("");
        telefone.setText("");
    }
}