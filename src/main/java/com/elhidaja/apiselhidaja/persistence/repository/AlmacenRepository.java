package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.almacen.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.almacen.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.AlmacenDAO;

@Repository
public class AlmacenRepository implements  AlmacenDAO  {
        private final JdbcTemplate jdbc;

    public AlmacenRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseAlmacenAllDTO getAllD(RequestAlmacenOptionDTO option) {
        ResponseAlmacenAllDTO rp = new ResponseAlmacenAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_almacenes");

            Map<String, Object> inParams = Map.of(
                    "option", option.getOption());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseAlmacenDTO> almacenes = rows.stream().map(row -> {
                ResponseAlmacenDTO dto = new ResponseAlmacenDTO();
                dto.setId_almacen(((Number) row.get("id_almacen")).longValue());
                dto.setCodigo((String) row.get("codigo"));
                dto.setDescripcion((String) row.get("descripcion"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setAlmacenes(almacenes);
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
    public ResponseDetalleAlmacenDTO getByIdD(RequestAlmacenIdDTO id) {
        ResponseDetalleAlmacenDTO rp = new ResponseDetalleAlmacenDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_almacen_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_almacen", id.getId());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            if (rows != null && !rows.isEmpty()) {
                Map<String, Object> row = rows.get(0);

                if (row.containsKey("exito")) {
                    rp.setExito((Boolean) row.get("exito"));
                    rp.setMensaje((String) row.get("mensaje"));
                    rp.setCodigo("404");
                } else {
                    ResponseAlmacenDTO dto = new ResponseAlmacenDTO();
                    dto.setId_almacen(((Number) row.get("id_almacen")).longValue());
                    dto.setCodigo((String) row.get("codigo"));
                    dto.setDescripcion((String) row.get("descripcion"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setAlmacen(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Almacén encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el almacén");
            }
        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseAlmacenMensajeDTO desactivateD(RequestAlmacenIdDTO id) {
        ResponseAlmacenMensajeDTO rp = new ResponseAlmacenMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_almacen");

            Map<String, Object> inParams = Map.of(
                    "id_almacen", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el almacén.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Almacén desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el almacén: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseAlmacenMensajeDTO activateD(RequestAlmacenIdDTO id) {
        ResponseAlmacenMensajeDTO rp = new ResponseAlmacenMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_almacen");

            Map<String, Object> inParams = Map.of(
                    "id_almacen", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el almacén.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Almacén activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el almacén: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseAlmacenMensajeDTO insertD(RequestAlmacenInsertDTO objAlmacen) {
        ResponseAlmacenMensajeDTO rp = new ResponseAlmacenMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_almacen");

            Map<String, Object> inParams = Map.of(
                    "codigo", objAlmacen.getCodigo(),
                    "descripcion", objAlmacen.getDescripcion());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el almacén.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Almacén registrado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar almacén: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseAlmacenMensajeDTO updateD(RequestAlmacenUpdateDTO objAlmacen) {
        ResponseAlmacenMensajeDTO rp = new ResponseAlmacenMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_almacen");

            Map<String, Object> inParams = Map.of(
                    "id_almacen", objAlmacen.getId(),
                    "codigo", objAlmacen.getCodigo(),
                    "descripcion", objAlmacen.getDescripcion());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el almacén.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Almacén actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el almacén: " + e.getMessage());
        }
        return rp;
    }
}
