package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.unidadMedidaProducto.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.UnidadMedidaProductoDAO;

@Repository
public class ProductoUnidadMedidaRepository implements UnidadMedidaProductoDAO {
    private final JdbcTemplate jdbc;

    public ProductoUnidadMedidaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseProductoUnidadMedidaAllDTO getAllD(RequestProductoUnidadMedidaOptionDTO option) {
        ResponseProductoUnidadMedidaAllDTO rp = new ResponseProductoUnidadMedidaAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_producto_unidad_medidas");

            Map<String, Object> inParams = Map.of(
                    "option", option.getOption());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponseProductoUnidadMedidaDTO> lista = rows.stream().map(row -> {
                ResponseProductoUnidadMedidaDTO dto = new ResponseProductoUnidadMedidaDTO();
                dto.setId(((Number) row.get("id_producto_unidad_medida")).longValue());
                dto.setProducto((String) row.get("producto"));
                dto.setUnidadMedida((String) row.get("unidad_medida"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setProductoUnidadMedidas(lista);

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
    public ResponseDetalleProductoUnidadMedidaDTO getByIdD(RequestProductoUnidadMedidaIdDTO id) {
        ResponseDetalleProductoUnidadMedidaDTO rp = new ResponseDetalleProductoUnidadMedidaDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_producto_unidad_medida_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_producto_unidad_medida", id.getId());

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

                    ResponseProductoUnidadMedidaDTO rcd = new ResponseProductoUnidadMedidaDTO();
                    rcd.setId(((Number) row.get("id_producto_unidad_medida")).longValue());
                    rcd.setProducto((String) row.get("producto"));
                    rcd.setUnidadMedida((String) row.get("unidad_medida"));
                    rcd.setStatus((Boolean) row.get("status"));

                    rp.setProductoUnidadMedida(rcd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Producto unidad medida encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el producto unidad medida");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserProductoUnidadMedidaMensajeDTO activateD(RequestProductoUnidadMedidaIdDTO id) {
        ResponserProductoUnidadMedidaMensajeDTO rp = new ResponserProductoUnidadMedidaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_producto_unidad_medida");

            Map<String, Object> inParams = Map.of(
                    "id_producto_unidad_medida", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el producto unidad medida.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Producto unidad medida activada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el producto unidad medida: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserProductoUnidadMedidaMensajeDTO desactivateD(RequestProductoUnidadMedidaIdDTO id) {

        ResponserProductoUnidadMedidaMensajeDTO rp = new ResponserProductoUnidadMedidaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_producto_unidad_medida");

            Map<String, Object> inParams = Map.of(
                    "id_producto_unidad_medida", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el producto unidad medida.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Producto unidad medida desactivada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el producto unidad medida: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserProductoUnidadMedidaMensajeDTO updateD(RequestProductoUnidadMedidaUpdateDTO obj) {
        ResponserProductoUnidadMedidaMensajeDTO rp = new ResponserProductoUnidadMedidaMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_producto_unidad_medida");

            Map<String, Object> inParams = Map.of(
                    "id_producto_unidad_medida", obj.getId(),
                    "id_unidad_medida", obj.getIdUnidadMedida());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el producto unidad medida.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Producto unidad medida actualizada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el producto unidad medida: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserProductoUnidadMedidaMensajeDTO insertD(RequestProductoUnidadMedidaInsertDTO obj) {

        ResponserProductoUnidadMedidaMensajeDTO rp = new ResponserProductoUnidadMedidaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_producto_unidad_medida");

            Map<String, Object> inParams = Map.of(
                    "id_producto", obj.getId_producto(),
                    "id_unidad_medida", obj.getId_unidad_medida());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el producto unidad medida.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Producto unidad medida registrada correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar producto unidad medida: " + e.getMessage());
        }
        return rp;
    }
}
