package com.elhidaja.apiselhidaja.persistence.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.elhidaja.apiselhidaja.presentation.dto.pallet.Request.*;
import com.elhidaja.apiselhidaja.presentation.dto.pallet.Response.*;
import com.elhidaja.apiselhidaja.service.DAO.PalletDAO;

@Repository
public class PalletRepository implements PalletDAO {
    private final JdbcTemplate jdbc;

    public PalletRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ResponsePalletAllDTO getAllD(RequestPalletOptionDTO option) {
        ResponsePalletAllDTO rp = new ResponsePalletAllDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_pallets");

            Map<String, Object> inParams = Map.of(
                    "option", option.getOption());

            Map<String, Object> result = call.execute(inParams);

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rows = (List<Map<String, Object>>) result.get("#result-set-1");

            List<ResponsePalletDTO> pallets = rows.stream().map(row -> {
                ResponsePalletDTO dto = new ResponsePalletDTO();
                dto.setId(((Number) row.get("id_pallet")).longValue());
                dto.setAlmacen((String) row.get("codigo_almacen"));
                dto.setEstante((String) row.get("codigo_estante"));
                dto.setCodigo((String) row.get("codigo_pallet"));
                dto.setDescripcion((String) row.get("descripcion_pallet"));
                dto.setStatus((Boolean) row.get("status"));
                return dto;
            }).toList();

            rp.setPallets(pallets);

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
    public ResponseDetallePalletDTO getByIdD(RequestPalletIdDTO id) {
        ResponseDetallePalletDTO rp = new ResponseDetallePalletDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_obtener_pallet_por_id");

            Map<String, Object> inParams = Map.of(
                    "id_pallet", id.getId());

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

                    ResponsePalletDTO rpd = new ResponsePalletDTO();
                    rpd.setId(((Number) row.get("id_pallet")).longValue());
                    rpd.setAlmacen((String) row.get("codigo_almacen"));
                    rpd.setEstante((String) row.get("codigo_estante"));
                    rpd.setCodigo((String) row.get("codigo_pallet"));
                    rpd.setDescripcion((String) row.get("descripcion_pallet"));
                    rpd.setStatus((Boolean) row.get("status"));

                    rp.setPallet(rpd);
                    rp.setExito(true);
                    rp.setCodigo("200");
                    rp.setMensaje("Pallet encontrado");
                }
            } else {
                rp.setExito(false);
                rp.setCodigo("404");
                rp.setMensaje("No se encontró el pallet");
            }

        } catch (Exception e) {
            rp.setExito(false);
            rp.setCodigo("500");
            rp.setMensaje("Error al obtener el dato " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserPalletMensajeDTO activateD(RequestPalletIdDTO id) {
        ResponserPalletMensajeDTO rp = new ResponserPalletMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_activar_pallet");

            Map<String, Object> inParams = Map.of(
                    "id_pallet", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo activar el pallet.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Pallet activado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al activar el pallet: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserPalletMensajeDTO desactivateD(RequestPalletIdDTO id) {
        ResponserPalletMensajeDTO rp = new ResponserPalletMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_desactivar_pallet");

            Map<String, Object> inParams = Map.of(
                    "id_pallet", id.getId());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo desactivar el pallet.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Pallet desactivado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al desactivar el pallet: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserPalletMensajeDTO updateD(RequestPalletUpdateDTO objPallet) {
        ResponserPalletMensajeDTO rp = new ResponserPalletMensajeDTO();

        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_actualizar_pallet");

            Map<String, Object> inParams = Map.of(
                    "id_pallet", objPallet.getId(),
                    "codigo", objPallet.getCodigo(),
                    "descripcion", objPallet.getDescripcion(),
                    "id_estante", objPallet.getIdEstante()
                    );

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo actualizar el pallet.");
            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Pallet actualizado correctamente.");
            }

        } catch (Exception e) {
            rp.setCodigo("500");
            rp.setMensaje("Error al actualizar el pallet: " + e.getMessage());
        }
        return rp;
    }

    @Override
    public ResponserPalletMensajeDTO insertD(RequestPalletInsertDTO objPallet) {
        ResponserPalletMensajeDTO rp = new ResponserPalletMensajeDTO();
        try {
            SimpleJdbcCall call = new SimpleJdbcCall(jdbc)
                    .withProcedureName("SP_insertar_pallet");

            Map<String, Object> inParams = Map.of(
                    "codigo", objPallet.getCodigo(),
                    "descripcion", objPallet.getDescripcion(),
                    "id_estante", objPallet.getIdEstante());

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
                rp.setMensaje(mensajeError != null ? mensajeError : "No se pudo registrar el pallet.");

            } else {
                rp.setExito(true);
                rp.setCodigo("200");
                rp.setMensaje("Pallet registrado correctamente");
            }

        } catch (Exception e) {
            e.printStackTrace();
            rp.setCodigo("500");
            rp.setMensaje("Error al insertar pallet : " + e.getMessage());
        }
        return rp;
    }
}
