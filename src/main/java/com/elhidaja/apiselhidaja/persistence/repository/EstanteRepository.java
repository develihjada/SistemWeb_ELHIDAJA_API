package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.estante.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.estante.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.EstanteDAO;
@Repository

public class EstanteRepository implements EstanteDAO {
        private final JdbcTemplate jdbc;

    public EstanteRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseEstanteAllDTO getAllD(RequestEstanteOptionDTO option) {
        ResponseEstanteAllDTO rp = new ResponseEstanteAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_estantes");

            Map<String, Object> inParams = Map.of("option", option.getOption());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseEstanteDTO> estantes = rows.stream().map(row -> {
                ResponseEstanteDTO dto = new ResponseEstanteDTO();
                    dto.setId(((Number) row.get("id_estante")).longValue());
                    dto.setAlmacen((String) row.get("codigo_almacen"));
                    dto.setUbicacion((String) row.get("descripcion_ubicacion"));
                    dto.setCodigo((String) row.get("codigo_estante"));
                    dto.setDescripcion((String) row.get("descripcion_estante"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setEstantes(estantes);
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
    public ResponseDetalleEstanteDTO getByIdD(RequestEstanteIdDTO id) {
        ResponseDetalleEstanteDTO rp = new ResponseDetalleEstanteDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_estante_por_id");

            Map<String, Object> inParams = Map.of("id_estante", id.getId());

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
                    ResponseEstanteDTO dto = new ResponseEstanteDTO();
                    dto.setId(((Number) row.get("id_estante")).longValue());
                    dto.setAlmacen((String) row.get("codigo_almacen"));
                    dto.setUbicacion((String) row.get("descripcion_ubicacion"));
                    dto.setCodigo((String) row.get("codigo_estante"));
                    dto.setDescripcion((String) row.get("descripcion_estante"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setEstante(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Estante encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el estante");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseEstanteMensajeDTO desactivateD(RequestEstanteIdDTO id) {
        ResponseEstanteMensajeDTO rp = new ResponseEstanteMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_estante");

            Map<String, Object> inParams = Map.of("id_estante", id.getId());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                rp.setExito(false);
                rp.setCodigo("400");
                rp.setMensaje((String) errorRow.get("mensaje"));
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Estante desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el estante: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseEstanteMensajeDTO activateD(RequestEstanteIdDTO id) {
        ResponseEstanteMensajeDTO rp = new ResponseEstanteMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_estante");

            Map<String, Object> inParams = Map.of("id_estante", id.getId());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                rp.setExito(false);
                rp.setCodigo("400");
                rp.setMensaje((String) errorRow.get("mensaje"));
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Estante activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el estante: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseEstanteMensajeDTO insertD(RequestEstanteInsertDTO objEstante) {
        ResponseEstanteMensajeDTO rp = new ResponseEstanteMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_estante");

            Map<String, Object> inParams = Map.of(
                    "codigo", objEstante.getCodigo(),
                    "descripcion", objEstante.getDescripcion());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                rp.setCodigo("400");
                rp.setMensaje((String) errorRow.get("mensaje"));
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Estante registrado correctamente");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar estante: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseEstanteMensajeDTO updateD(RequestEstanteUpdateDTO objEstante) {
        ResponseEstanteMensajeDTO rp = new ResponseEstanteMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_estante");

            Map<String, Object> inParams = Map.of(
                    "id_estante", objEstante.getId(),
                    "codigo", objEstante.getCodigo(),
                    "descripcion", objEstante.getDescripcion());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> errorRow = resultSet.get(0);
                rp.setCodigo("400");
                rp.setMensaje((String) errorRow.get("mensaje"));
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Estante actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar estante: " + e.getMessage());
        }
        return rp;
    }
}
