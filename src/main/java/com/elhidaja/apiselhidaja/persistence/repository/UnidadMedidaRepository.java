package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.ResponseDetalleUnidadMedidaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.ResponseUnidadMedidaAllDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.ResponseUnidadMedidaDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Response.ResponseUnidadMedidaMensajeDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaIdDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaInsertDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaOptionDTO;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest.RequestUnidadMedidaUpdateDTO;
import com.elhidaja.apiselhidaja.service.DAO.UnidadMedidaDAO;

@Repository
public class UnidadMedidaRepository implements UnidadMedidaDAO {
    private final JdbcTemplate jdbc;

    public UnidadMedidaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseUnidadMedidaAllDTO getAllD(RequestUnidadMedidaOptionDTO option) {
        ResponseUnidadMedidaAllDTO rp = new ResponseUnidadMedidaAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_unidad_medidas");

            Map<String, Object> inParams = Map.of("option", option.getOption());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseUnidadMedidaDTO> lista = rows.stream().map(row -> {
                ResponseUnidadMedidaDTO dto = new ResponseUnidadMedidaDTO();
                dto.setId(((Number) row.get("id_unidad_medida")).longValue());
                dto.setNombre((String) row.get("nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setUnidadMedidas(lista);
            rp.setExito(true);
            rp.setCodigo("200");
            rp.setMensaje("Consulta exitosa");

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener datos: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseDetalleUnidadMedidaDTO getByIdD(RequestUnidadMedidaIdDTO id) {
        ResponseDetalleUnidadMedidaDTO rp = new ResponseDetalleUnidadMedidaDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_unidad_medida_por_id");

            Map<String, Object> inParams = Map.of("id_unidad_medida", id.getId());

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
                    ResponseUnidadMedidaDTO dto = new ResponseUnidadMedidaDTO();
                    dto.setId(((Number) row.get("id_unidad_medida")).longValue());
                    dto.setNombre((String) row.get("nombre"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setUnidadMedida(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Unidad de medida encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la unidad de medida");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseUnidadMedidaMensajeDTO updateD(RequestUnidadMedidaUpdateDTO obj) {
        ResponseUnidadMedidaMensajeDTO rp = new ResponseUnidadMedidaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_unidad_medida");

            Map<String, Object> inParams = Map.of(
                    "id_unidad_medida", obj.getId(),
                    "nombre", obj.getNombre());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> row = resultSet.get(0);
                rp.setCodigo("400");
                rp.setMensaje((String) row.getOrDefault("mensaje", "No se pudo actualizar la unidad de medida."));
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Unidad de medida actualizada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar la unidad de medida: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseUnidadMedidaMensajeDTO insertD(RequestUnidadMedidaInsertDTO obj) {
        ResponseUnidadMedidaMensajeDTO rp = new ResponseUnidadMedidaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_unidad_medida");

            Map<String, Object> inParams = Map.of("nombre", obj.getNombre());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resultSet = (List<Map<String, Object>>) result.values().stream()
                    .filter(v -> v instanceof List && !((List<?>) v).isEmpty())
                    .findFirst()
                    .orElse(null);

            if (resultSet != null) {
                Map<String, Object> row = resultSet.get(0);
                rp.setCodigo("400");
                rp.setMensaje((String) row.getOrDefault("mensaje", "No se pudo insertar la unidad de medida."));
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Unidad de medida registrada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar unidad de medida: " + e.getMessage());
        }

        return rp;
    }

    @Override
    public ResponseUnidadMedidaMensajeDTO activateD(RequestUnidadMedidaIdDTO id) {
        ResponseUnidadMedidaMensajeDTO rp = new ResponseUnidadMedidaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_unidad_medida");

            Map<String, Object> inParams = Map.of(
                    "id_unidad_medida", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la unidad de medida.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Unidad de medida activada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar la unidad de medida: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponseUnidadMedidaMensajeDTO desactivateD(RequestUnidadMedidaIdDTO id) {
        ResponseUnidadMedidaMensajeDTO rp = new ResponseUnidadMedidaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_unidad_medida");

            Map<String, Object> inParams = Map.of(
                    "id_unidad_medida", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la unidad de medida.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Unidad de medida desactivada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar la unidad de medida: " + e.getMessage());
        }
        return rp;
    }
}
