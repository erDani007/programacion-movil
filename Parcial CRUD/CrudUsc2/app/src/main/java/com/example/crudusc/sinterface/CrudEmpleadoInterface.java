package com.example.crudusc.sinterface;

import com.example.crudusc.model.Empleado;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CrudEmpleadoInterface {

    @GET("/consultarAll")
    Call<List<Empleado>> getAll();

    @DELETE("/nombre/{id}")
    Call<Void> eliminarEmpleado(@Path("id") int idEmpleado);

    @POST("/guardar")
    Call<Empleado> createEmployee(@Body Empleado empleado);

    @PUT("/actualizar/{id}")
    Call<Empleado> actualizarEmpleado(@Path("id") int idEmpleado, @Body Empleado empleado);
}
