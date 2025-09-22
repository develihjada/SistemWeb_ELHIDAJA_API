package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Response.*;
import com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Resquest.*;
import com.elhidaja.apiselhidaja.service.DAO.SubCategoriaDAO;

@Repository
public class SubCategoriaRepository implements SubCategoriaDAO {
    private final JdbcTemplate jdbc;

    public SubCategoriaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponseSubCategoriAllDTO getAllD(ResquestSubCategoriaOptionDTO option) {
        ResponseSubCategoriAllDTO rp = new ResponseSubCategoriAllDTO();
        System.out.println(option.getOption());
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_sub_categorias");

            Map<String, Object> inParams = Map.of(
                    "option", option.getOption());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");
            rows.forEach(row -> System.out.println("Keys: " + row.keySet()));
            List<ResponseSubCategoriaDTO> subCategorias = rows.stream().map(row -> {
                ResponseSubCategoriaDTO dto = new ResponseSubCategoriaDTO();
                dto.setId(((Number) row.get("id_sub_categoria")).longValue());
                dto.setCategoria((String) row.get("categoria_nombre"));
                dto.setNombre((String) row.get("sub_categoria_nombre"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setSubCategorias(subCategorias);
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
    public ResponseDetalleSubCategoriaDTO getByIdD(RequestSubCategoriaIdDTO id) {
        ResponseDetalleSubCategoriaDTO rp = new ResponseDetalleSubCategoriaDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_sub_categoria_por_id");

            Map<String, Object> inParams = Map.of("id_sub_categoria", id.getId());
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

                    ResponseSubCategoriaDTO dto = new ResponseSubCategoriaDTO();
                    dto.setId(((Number) row.get("id_sub_categoria")).longValue());
                    dto.setCategoria((String) row.get("categoria_nombre"));
                    dto.setNombre((String) row.get("sub_categoria_nombre"));
                    dto.setStatus((Boolean) row.get("status"));

                    rp.setSubCategoria(dto);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Subcategoría encontrada");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró la sub categoría");
            }
        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener la subcategoría: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserSubCategoriaMensajeDTO insertD(RequestSubCategoriaInsertDTO dto) {
        ResponserSubCategoriaMensajeDTO rp = new ResponserSubCategoriaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_sub_categoria");

            Map<String, Object> inParams = Map.of(
                    "id_categoria", dto.getIdCategoria(),
                    "nombre", dto.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar la subcategoría.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Subcategoría registrada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar subcategoría: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserSubCategoriaMensajeDTO updateD(RequestSubCategoriaUpdateDTO dto) {
        ResponserSubCategoriaMensajeDTO rp = new ResponserSubCategoriaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_sub_categoria");

            Map<String, Object> inParams = Map.of(
                    "id_sub_categoria", dto.getId(),
                    "nombre", dto.getNombre());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar la subcategoría.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Subcategoría actualizada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar subcategoría: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserSubCategoriaMensajeDTO activateD(RequestSubCategoriaIdDTO id) {
        ResponserSubCategoriaMensajeDTO rp = new ResponserSubCategoriaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_sub_categoria");

            Map<String, Object> inParams = Map.of("id_sub_categoria", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar la subcategoría.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Subcategoría activada correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar subcategoría: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserSubCategoriaMensajeDTO desactivateD(RequestSubCategoriaIdDTO id) {
        ResponserSubCategoriaMensajeDTO rp = new ResponserSubCategoriaMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_sub_categoria");

            Map<String, Object> inParams = Map.of(
                    "id_sub_categoria", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar la subcategoría.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Subcategoría desactivada ---correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar subcategoría: " + e.getMessage());
        }
        return rp;
    }

}
