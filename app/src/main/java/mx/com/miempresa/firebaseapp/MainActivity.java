package mx.com.miempresa.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {


    //Declaracion de objetos para recibir los elementos de la vista
    private EditText etxTema;
    private Spinner spiCarrera,spiMateria;
    private Button btnReg;
    //Referencia a base de datos
    private DatabaseReference refDiario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refDiario = FirebaseDatabase.getInstance().getReference("Clase");

        //Asociar con los objetos de la vista
        etxTema = (EditText)findViewById(R.id.etxTema);
        spiCarrera = (Spinner)findViewById(R.id.spiCarrera);
        spiMateria = (Spinner)findViewById(R.id.spiMateria);
        btnReg = (Button)findViewById(R.id.btnRegistrar);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarClase();
            }
        });
    }//Fin metodo OnCreate

    public void registrarClase(){
        String carrera = spiCarrera.getSelectedItem().toString();
        String materia = spiMateria.getSelectedItem().toString();
        String tema = etxTema.getText().toString();

        if(!TextUtils.isEmpty(tema)){
            String id = refDiario.push().getKey();
            Clase leccion = new Clase(id,carrera,materia,tema);
            refDiario.child("Lecciones").child(id).setValue(leccion);
            Toast.makeText(this,"Clase registrada", Toast.LENGTH_LONG);

        }else{
            Toast.makeText(this,"Ingresar tema", Toast.LENGTH_LONG);
        }
    }
}
