package com.example.crudusc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.crudusc.sinterface.CrudEmpleadoInterface;
import com.example.crudusc.model.Empleado;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    List<Empleado> listEmpleado;
    CrudEmpleadoInterface cruempleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAll();
    }
    private void getAll(){
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://localhost:8080/")
                .baseUrl("http://192.168.101.75:8081/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<List<Empleado>> call = cruempleado.getAll();

        call.enqueue(new Callback<List<Empleado>>(){

            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if(!response.isSuccessful()) {
                    //System.out.println(response.message());
                    Log.e("Response err:, " , response.message());
                    return;
                }
                listEmpleado = response.body();
                //listEmpleado.forEach(p-> System.out.println(p.toString()));
                listEmpleado.forEach(p-> Log.i("Empleados: ", p.toString()));

            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t){
                Log.e("Throw error:" , t.getMessage());
                //System.out.println(t.getMessage());
            }
        });

    }


}