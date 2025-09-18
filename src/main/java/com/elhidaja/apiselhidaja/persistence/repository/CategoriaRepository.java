package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.categoria.Response.ResponseCategoriAllDTO;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Response.ResponseCategoriaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Response.ResponseDetalleCategoriaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Response.ResponserCategoriaMensajeDTO;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest.RequestCategoriaInsertDTO;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest.RequestCategoriaUpdateDTO;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest.RequesteCategoriaIdDTO;
import com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest.ResquestCategoriaOptionDTO;
import com.elhidaja.apiselhidaja.service.DAO.CategoriaDAO;

@Repository
public class CategoriaRepository implements CategoriaDAO {

    private final JdbcTemplate jdbc;

    public CategoriaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseCategoriAllDTO getAllD(ResquestCategoriaOptionDTO option) {
        ResponseCategoriAllDTO rp = new ResponseCategoriAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_categorias");

            // Parámetros de entrada del SP ption
            Map<String, Object> inParams = Map.of(
                    "option", option.getOption());

            // Ejecutar el SP
            Map<String, Object> result = call.execute(inParams);

            // Obtener los datos
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            // Convertir cada fila en un ResponseCategoriaDTO
            List<ResponseCategoriaDTO> categorias = rows.stream().map(row -> {
                ResponseCategoriaDTO dto = new ResponseCategoriaDTO();
                dto.setId_categoria(((Number) row.get("id_categoria")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setListaCategoria(categorias);

            rp.setExito(true);
            rp.setCodigo("200");
            rp.setMensaje("Consulta exitosa");
        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener datos " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleCategoriaDTO getByIdD(RequesteCategoriaIdDTO id) {
        ResponseDetalleCategoriaDTO rp = new ResponseDetalleCategoriaDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_categoria_por_id");

            // Parámetros de entrada del SP ption
            Map<String, Object> inParams = Map.of(
                    "id_categoria", id.getId_categoria());

            // Ejecutar el SP
            Map<String, Object> result = call.execute(inParams);

            // Obtener los datos
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            if (rows != null && !rows.isEmpty()) {
                Map<String, Object> row = rows.get(0);

                if (row.containsKey("exito")) {
                    rp.setExito((Boolean) row.get("exito"));
                    rp.setMensaje((String) row.get("mensaje"));
                    rp.setCodigo("404");
                } else {

                    ResponseCategoriaDTO rcd = new ResponseCategoriaDTO();
                    rcd.setId_categoria(((Number) row.get("id_categoria")).longValue());
                    rcd.setNombre((String) row.get("nombre"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setResponse(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("categoria encontrada");
                }
            }
            else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la categoría");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserCategoriaMensajeDTO activateD(RequesteCategoriaIdDTO id) {
        ResponserCategoriaMensajeDTO rp = new ResponserCategoriaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_categoria");

            // Parámetros de entrada del SP ID y nombre
            Map<String, Object> inParams = Map.of(
                    "id_categoria", id.getId_categoria());

            // Ejecutar el SP
            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                String mensajeError = (String) errorRow.get("mensaje");
                rp.setExito(false);
                rp.setCodigo("400");
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la categoría.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Categoría activar correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar la categoría: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserCategoriaMensajeDTO desactivateD(RequesteCategoriaIdDTO id) {

        ResponserCategoriaMensajeDTO rp = new ResponserCategoriaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_categoria");

            // Parámetros de entrada del SP ID y nombre
            Map<String, Object> inParams = Map.of(
                    "id_categoria", id.getId_categoria());

            // Ejecutar el SP
            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                String mensajeError = (String) errorRow.get("mensaje");
                rp.setExito(false);
                rp.setCodigo("400");
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la categoría.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Categoría desactivada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar la categoría: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserCategoriaMensajeDTO updateD(RequestCategoriaUpdateDTO objCategoria) {
        ResponserCategoriaMensajeDTO rp = new ResponserCategoriaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_categoria");

            // Parámetros de entrada del SP ID y nombre
            Map<String, Object> inParams = Map.of(
                    "id_categoria", objCategoria.getId_categoria(),
                    "nombre", objCategoria.getNombre());

            // Ejecutar el SP
            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                String mensajeError = (String) errorRow.get("mensaje");

                rp.setCodigo("400");
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar la categoría.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Categoría actualizada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar la categoría: " + e.getMessage());
        }
        return rp;

    }

    @Override
    public ResponserCategoriaMensajeDTO insertD(RequestCategoriaInsertDTO objCategoria) {

        ResponserCategoriaMensajeDTO rp = new ResponserCategoriaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_categoria");

            // Parámetros de entrada del SP
            Map<String, Object> inParams = Map.of(
                    "nombre", objCategoria.getNombre());

            // Ejecutar el SP
            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {

                Map<String, Object> errorRow = resultSet.get(0);
                String mensajeError = (String) errorRow.get("mensaje");

                rp.setCodigo("400");
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar la categoria.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("categoria registrada correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar categoria : " + e.getMessage());
        }
        return rp;
    }

}
