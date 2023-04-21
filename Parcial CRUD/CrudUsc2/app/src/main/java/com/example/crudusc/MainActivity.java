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

        //obtener todos
        getAll();

        //eliminar empleado por ID
        eliminarEmpleado(11);

        //crear empleado nuevo
        Empleado empleado = new Empleado();
        empleado.setNombre("Juan2");
        empleado.setPassword("1234567");
        empleado.setEmail("juan@gmail.comm");
        createEmployee(empleado);

        //actualizar empleado
        actualizarEmpleado(3, empleado);
    }
    private void getAll(){
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://localhost:8080/")
                .baseUrl("http://192.168.101.75:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<List<Empleado>> call = cruempleado.getAll();

        call.enqueue(new Callback<List<Empleado>>(){

            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if(!response.isSuccessful()) {
                    //System.out.println(response.message());
                    Log.i("respuesta:, " , response.body().toString());
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

    private void eliminarEmpleado(int idEmpleado) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.101.75:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Void> call = cruempleado.eliminarEmpleado(idEmpleado);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()) {
                    Log.i("respuesta:, " , response.message());
                    return;
                }
                Log.i("respuesta:, " , "Empleado eliminado correctamente");

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t){
                Log.e("Throw error:" , t.getMessage());
            }
        });

    }

    private void createEmployee(Empleado empleado) {
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://localhost:8080/")
                .baseUrl("http://192.168.101.75:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.createEmployee(empleado);

        call.enqueue(new Callback<Empleado>() {
            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if (!response.isSuccessful()) {
                    Log.i("Error: ", response.message());
                    return;
                }
                Empleado empleadoResponse = response.body();
                Log.i("Empleado creado: ", empleadoResponse.toString());
            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }

    private void actualizarEmpleado( int idEmpleado, Empleado empleado){
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("http://localhost:8080/")
                .baseUrl("http://192.168.101.75:8081")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cruempleado = retrofit.create(CrudEmpleadoInterface.class);
        Call<Empleado> call = cruempleado.actualizarEmpleado(idEmpleado, empleado);

        call.enqueue(new Callback<Empleado>(){

            @Override
            public void onResponse(Call<Empleado> call, Response<Empleado> response) {
                if(!response.isSuccessful()) {
                    //System.out.println(response.message());
                    Log.i("respuesta:, " , response.body().toString());
                    return;
                }
                Empleado empleadoActualizado = response.body();
                //System.out.println("Empleado actualizado: " + empleadoActualizado.toString());
                Log.i("Empleado actualizado: ", empleadoActualizado.toString());

            }

            @Override
            public void onFailure(Call<Empleado> call, Throwable t){
                Log.e("Throw error:" , t.getMessage());
                //System.out.println(t.getMessage());
            }
        });

    }
}






