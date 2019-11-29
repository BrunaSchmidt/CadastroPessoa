package com.example.cadastropessoa;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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


    private EditText campoBuscar;
    private Button botaoBuscar;
    private TextView campoNome;
    private TextView campoSobrenome;
    private TextView campoNascimento;
    private TextView campoCPF;
    private TextView campoEstadoC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Salva dados no Firebase

        DatabaseReference usuarios = referencia.child( "usuarios" );
        Usuario usuario = new Usuario();
        usuario.setNome("jonas");
        usuario.setSobrenome("souza");
        usuario.setDatanascimento("12/11/99");
        usuario.setCpf("3214567890");
        usuario.setEstadocivil("solteiro");
        usuarios.child("003").setValue( usuario );

        usuario.setNome("jose");
        usuario.setSobrenome("silva");
        usuario.setDatanascimento("02/05/89");
        usuario.setCpf("7865453999");
        usuario.setEstadocivil("casado");
        usuarios.child("005").setValue( usuario );

        usuario.setNome("ana");
        usuario.setSobrenome("moraes");
        usuario.setDatanascimento("21/10/99");
        usuario.setCpf("1234567821");
        usuario.setEstadocivil("solteiro");
        usuarios.child("006").setValue( usuario );


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



       // Busca Usuário

        campoBuscar = (EditText) findViewById(R.id.fieldBuscar);
        botaoBuscar = (Button) findViewById(R.id.buttonBuscar);
        campoNome = (TextView) findViewById(R.id.campoNome);
        campoSobrenome = (TextView) findViewById(R.id.campoSobrenome);
        campoNascimento = (TextView) findViewById(R.id.campoNascimento);
        campoCPF = (TextView) findViewById(R.id.campoCPF);
        campoEstadoC = (TextView) findViewById(R.id.campoEstadoC);



        botaoBuscar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Pega o dado do campo e guarda em uma variavém para posteriormente realizar a busca
                String dado = campoBuscar.getText().toString();

                if(dado != null ) {

                    //Cria uma referencia apontando para usuarios
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("usuarios");

                    //Guarda a referencia de usuario ordenando pela child nome e em seguida compara o conteúdo dele com o conteúdo da variável  dado
                    Query user = userRef.orderByChild("nome").equalTo(dado);

                    user.addChildEventListener(new ChildEventListener() {
                        //através da referencia user, pega o dado de acordo com a childkey anterior e aloca cada informação no devido campo de acordo com user buscado
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                            Usuario peganome = dataSnapshot.getValue(Usuario.class);
                            campoNome.setText("Nome: "+ peganome.getNome());
                            Usuario pegasobre = dataSnapshot.getValue(Usuario.class);
                             campoSobrenome.setText("Sobrenome: " + pegasobre.getSobrenome());
                           Usuario peganasc = dataSnapshot.getValue(Usuario.class);
                           campoNascimento.setText("Data de Nascimento: "+ peganasc.getDatanascimento());
                           Usuario pegacpf = dataSnapshot.getValue(Usuario.class);
                           campoCPF.setText("CPF: "+ pegacpf.getCpf());
                            Usuario pegaestadoc = dataSnapshot.getValue(Usuario.class);
                            campoEstadoC.setText("Estado Civil: "+ pegaestadoc.getEstadocivil());

                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {}

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {}

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {}

                        @Override
                        public void onCancelled(DatabaseError databaseError) {}


                    });

                }/*else{

                    String resultado = "erro";

                    Context context = getApplicationContext();
                    String message = resultado ;
                    int length = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, message, length);
                    toast.show();

                }*/

            }



        });




    }
}