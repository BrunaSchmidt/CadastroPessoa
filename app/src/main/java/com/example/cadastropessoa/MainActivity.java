package com.example.cadastropessoa;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    //private FirebaseAuth useri ;
    private EditText campoBuscar;
    private Button botaoBuscar;
    private TextView campoInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //Deslogar usuario
        usuario.signOut();

        //Logar usuario
        usuario.signInWithEmailAndPassword(
                "cass78@gmail.com", "12345")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful() ){
                            Log.i("signIn", "Sucesso ao logar usuario!" );
                        }else {
                            Log.i("signIn", "Erro ao logar usuario!" );
                        }
                    }
                });
        */


/*
        //*Verifica usuario logado
        if( usuario.getCurrentUser() != null ){
            Log.i("CreateUser", "Usuario logado!" );
        }else {
            Log.i("CreateUser", "Usuario nao logado!" );
        }
*/
/*

        useri = FirebaseAuth.getInstance();
      //  Cadastro de usuario
        useri.createUserWithEmailAndPassword("cass789@gmail.com", "12345")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful() ){
                            Log.d("CreateUser", "Sucesso ao cadastrar usuario!" );


                        }else {
                            Log.d("CreateUser", "Erro ao cadastrar usuario!" );
                        }
                    }
                });
*/



        /*



        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, cassiano!");




*/


//Salvar dados no Firebase

        DatabaseReference usuarios = referencia.child( "usuarios" );
        Usuario usuario = new Usuario();
        usuario.setNome("FULANO");
        usuario.setSobrenome("CICLANO");
        usuario.setDatanascimento("10/11/70");
        usuario.setCpf(1234567890);
        usuario.setEstadocivil("casado");
        usuarios.child("003").setValue( usuario );

        usuario.setNome("jose");
        usuario.setSobrenome("silva");
        usuario.setDatanascimento("11/02/70");
        usuario.setCpf(1234567821);
        usuario.setEstadocivil("solteiro");
        usuarios.child("005").setValue( usuario );


        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString() );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


            DatabaseReference enderecos = referencia.child("enderecos");
            Endereco endereco = new Endereco();
            endereco.setLogradouro(123);
            endereco.setCep(3456);
            endereco.setCidade("porto alegre");
            endereco.setEstado("rio grande do sul");
            enderecos.child("002").setValue(endereco);

        enderecos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString() );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

            DatabaseReference formacoes = referencia.child("formacoes");
            Formacao formacao = new Formacao();
            formacao.setCursos("informatica");
            formacao.setGraduacao("analise e desenvolvimento de sistemas");
            formacao.setPosgraduacao("segurança");
            formacoes.child("004").setValue(formacao);

        formacoes.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("FIREBASE", dataSnapshot.getValue().toString() );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        // DatabaseReference produtos = referencia.child("produtos");

        //  Produto produto = new Produto();
        // produto.setDescricao("Acer Aspire");
        //  produto.setMarca("Acer");
        //  produto.setPreco(999.99);

        //  produto.child("002").setValue( produto );



       // Busca Usuário

        campoBuscar = (EditText) findViewById(R.id.fieldBuscar);
        botaoBuscar = (Button) findViewById(R.id.buttonBuscar);
        campoInfo = (TextView) findViewById(R.id.fieldInfo);



        botaoBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String dado = campoBuscar.getText().toString();




                if(dado != null ) {


                     DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("usuarios");

                    //userRef = FirebaseDatabase.getInstance().getReference();


                    Query user = userRef.orderByChild("nome").equalTo(dado);

                    user.addValueEventListener( new ValueEventListener(){
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot clienteSnap : dataSnapshot.getChildren() ){


                                campoInfo.setText();

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}

                    });

                }else{

                    String resultado = "erro";


                    Context context = getApplicationContext();
                    String message = resultado ;
                    int length = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, message, length);
                    toast.show();

                }





            }
        });




    }
}