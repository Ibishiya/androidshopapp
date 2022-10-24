package com.ecommerce.shoopingapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class CrudActivity extends AppCompatActivity {

    private EditText txtCodigo;
    private EditText txtDescripcion;
    private EditText txtPrecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        txtCodigo=(EditText) findViewById(R.id.txtCodigo);
        txtDescripcion=(EditText) findViewById(R.id.txtDescripcion);
        txtPrecio=(EditText) findViewById(R.id.txtPrecio);
    }

    public void registrarProductos(View view){
        CrudSql admin = new CrudSql(this,"inventario",null,1);
        SQLiteDatabase baseDatos=admin.getWritableDatabase();

        String codigo=txtCodigo.getText().toString();
        String descripcion=txtDescripcion.getText().toString();
        String precio= txtPrecio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo",codigo);
            registro.put("descripcion",descripcion);
            registro.put("precio",precio);

            baseDatos.insert("articulos",null,registro);

            baseDatos.close();
            txtCodigo.setText("");
            txtDescripcion.setText("");
            txtPrecio.setText("");
            Toast.makeText(this,"Registro se guardo exitosamente",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Los campos no pueden estar vacios",Toast.LENGTH_LONG).show();
        }
    }

    public void buscarProducto(View view){
        CrudSql admin = new CrudSql(this,"inventario",null,1);
        SQLiteDatabase baseDatos=admin.getWritableDatabase();

        String codigo = txtCodigo.getText().toString();
        if(!codigo.isEmpty()){
            Cursor fila =baseDatos.rawQuery("select descripcion,precio " +
                    "from articulos where codigo="+codigo,null);

            if(fila.moveToFirst()){
                txtDescripcion.setText(fila.getString(0));
                txtPrecio.setText(fila.getString(1));
                baseDatos.close();
            }else{
                Toast.makeText(this,"El producto con codigo "+codigo+" no existe",Toast.LENGTH_LONG).show();
            }

        }else{
            Toast.makeText(this,"El campo codigo no puede estar vacio",Toast.LENGTH_LONG).show();
        }
    }




}