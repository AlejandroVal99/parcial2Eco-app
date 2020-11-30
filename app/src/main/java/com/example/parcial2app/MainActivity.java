package com.example.parcial2app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button btn_enviarScore;
    TextView preguntaAct;
    EditText editT_puntaje;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_enviarScore = findViewById(R.id.btn_Sendscore);
        preguntaAct = findViewById(R.id.tV_question);
        editT_puntaje = findViewById(R.id.editT_score);

        db = FirebaseDatabase.getInstance();
        btn_enviarScore.setVisibility(View.INVISIBLE);

        db.getReference().child("parcial2").child("preActual").child("pregunta").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange( DataSnapshot snapshot) {

                        if(snapshot.exists()){
                            for(DataSnapshot child: snapshot.getChildren()){
                                Pregunta preAct = child.getValue(Pregunta.class);
                                preguntaAct.setText(preAct.getPregunta());
                            }
                            btn_enviarScore.setVisibility(View.VISIBLE);
                        }else{
                            preguntaAct.setText("No hay preguntas por el momento");
                            btn_enviarScore.setVisibility(View.INVISIBLE);
                        }

                    }

                    @Override
                    public void onCancelled( DatabaseError error) {

                    }
                }
        );


        btn_enviarScore.setOnClickListener(
                (v)->{
                    String puntaje = editT_puntaje.getText().toString().trim();
                    int validaPunt = Integer.parseInt(puntaje);
                    if(puntaje.isEmpty()){
                        Toast.makeText(this,"No has escrito niguna puntuacion",Toast.LENGTH_LONG).show();
                    }else{
                        if(validaPunt>0 && validaPunt<=10){
                            String id = db.getReference().child("parcial2").child("preActual").child("puntaje").push().getKey();
                            DatabaseReference ref = db.getReference().child("parcial2").child("preActual").child("puntaje").child(id);
                            Puntaje newPuntaje = new Puntaje(
                                    id,
                                    puntaje
                            );
                            ref.setValue(newPuntaje);
                            editT_puntaje.setText("");
                        }else{
                            Toast.makeText(this,"Solo valores entre 0 y 10",Toast.LENGTH_LONG).show();
                        }


                    }




                }
        );
    }
}