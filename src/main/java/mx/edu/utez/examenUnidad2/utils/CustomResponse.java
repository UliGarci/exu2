package mx.edu.utez.examenUnidad2.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CustomResponse <T>{
    T data;
    boolean error;
    int httpStatusCode;
    String message;
}
