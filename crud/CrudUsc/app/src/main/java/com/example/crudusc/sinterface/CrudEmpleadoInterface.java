package com.example.crudusc.sinterface;

import com.example.crudusc.model.Empleado;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface CrudEmpleadoInterface {

    @GET("/consultarAll")
    Call<List<Empleado>> getAll();

}
