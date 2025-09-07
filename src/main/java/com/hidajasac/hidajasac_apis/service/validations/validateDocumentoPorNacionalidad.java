package com.hidajasac.hidajasac_apis.service.validations;

import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Documento.DocumentoIdentidadEntity;
import com.hidajasac.hidajasac_apis.persistence.entity.usuarios.documentoIdentidad.Nacionalidad.NacionalidadEntity;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.NacionalidadRep.INacionalidad;
import com.hidajasac.hidajasac_apis.persistence.repository.documentoIdentidad.DocumentoRep.IDocumentoIdentidad;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class validateDocumentoPorNacionalidad {

    private  final IDocumentoIdentidad tipoRepo;
    private final INacionalidad nacionalidadRepo;

    public validateDocumentoPorNacionalidad(IDocumentoIdentidad tipoRepo, INacionalidad nacionalidadRepo) {
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

        NacionalidadEntity nacionalidad = nacionalidadRepo.findById(idNacionalidad)
                .orElseThrow(() -> new EntityNotFoundException("NacionalidadRep no encontrada"));

        // verifica que el tipo de documento está asociado a la nacionalidad
        boolean tipoDocumentoValido = nacionalidad.getDocumentoDeIdentidad().stream()
                .anyMatch(td -> td.getId().equals(idTipoDocumento));
        if (!tipoDocumentoValido) {
            throw new IllegalArgumentException("El tipo de documento no es válido para la nacionalidad seleccionada.");
        }

        DocumentoIdentidadEntity tipoDocumento = tipoRepo.findById(idTipoDocumento)
                .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado"));

        // valida longitud mínima y máxima
        int length = numeroDocumento.length();
        if (tipoDocumento.getLongitud() != length ) {
            throw new IllegalArgumentException("El número del documento de identidad deve ser de "+length+" caracteres");
        }
        // valida regex
        if (tipoDocumento.getTipoDocumento() != null &&
                !tipoDocumento.getTipoDocumento().validar(numeroDocumento)) {
            throw new IllegalArgumentException(tipoDocumento.getMensajeError());
        }
    }
}
