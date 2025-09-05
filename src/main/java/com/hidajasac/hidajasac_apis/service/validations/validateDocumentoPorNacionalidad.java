package com.hidajasac.hidajasac_apis.service.validations;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.TipoNacionalidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.tipoDocumento.TipoDocumentoIdentidadEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.Nacionalidad.ITipoNacionalidad;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.tipoDocumento.ITipoDocumentoIdentidad;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class validateDocumentoPorNacionalidad {

    private  final ITipoDocumentoIdentidad tipoRepo;
    private final ITipoNacionalidad nacionalidadRepo;

    public validateDocumentoPorNacionalidad(ITipoDocumentoIdentidad tipoRepo, ITipoNacionalidad nacionalidadRepo) {
        this.tipoRepo = tipoRepo;
        this.nacionalidadRepo = nacionalidadRepo;
    }

    /**
     * Valida el documento de la entidad userModel-> extiende a sus hijas
     * necesita el id de la nacionalidad ya que este esta asociado a el tipo de documento
     * el tipo de documento tiene una validacion la cual puede ser numerica alfanumerica o alfabetica
     * estas validaciones son enums la cual cada valor tiene su validacion regex
     * @param idNacionalidad
     * @param idTipoDocumento
     * @param numeroDocumento
     */
    public void validateDocumentoPorNacional(Long idNacionalidad, Long idTipoDocumento, String numeroDocumento) {

        TipoNacionalidadEntity nacionalidad = nacionalidadRepo.findById(idNacionalidad)
                .orElseThrow(() -> new EntityNotFoundException("Nacionalidad no encontrada"));

        // verifica que el tipo de documento está asociado a la nacionalidad
        boolean tipoDocumentoValido = nacionalidad.getTiposDeDocumento().stream()
                .anyMatch(td -> td.getId().equals(idTipoDocumento));
        if (!tipoDocumentoValido) {
            throw new IllegalArgumentException("El tipo de documento no es válido para la nacionalidad seleccionada.");
        }

        TipoDocumentoIdentidadEntity tipoDocumento = tipoRepo.findById(idTipoDocumento)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado"));

        // valida longitud mínima y máxima
        int length = numeroDocumento.length();
        if (tipoDocumento.getLongitudMinima() != null && length < tipoDocumento.getLongitudMinima()) {
            throw new IllegalArgumentException("El número de documento es demasiado corto para este tipo de documento.");
        }
        if (tipoDocumento.getLongitudMaxima() != null && length > tipoDocumento.getLongitudMaxima()) {
            throw new IllegalArgumentException("El número de documento es demasiado largo para este tipo de documento.");
        }
        // valida regex
        if (tipoDocumento.getValidacionRegex() != null &&
                !tipoDocumento.getValidacionRegex().validar(numeroDocumento)) {
            throw new IllegalArgumentException(tipoDocumento.getMensajeError());
        }
    }
}
