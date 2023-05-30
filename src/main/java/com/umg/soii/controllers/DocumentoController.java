package com.umg.soii.controllers;

import com.umg.soii.dao.DocumentoDao;
import com.umg.soii.dao.PropietarioDao;
import com.umg.soii.models.Documento;
import com.umg.soii.models.Propietario;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class DocumentoController {

    @Autowired
    private DocumentoDao documentoDao;

    @PostMapping("/cargarDocumento")
    public void guardarDocumento(@RequestParam("idFile") MultipartFile file,
                                 @RequestParam("txtIdDoc") String idenDoc,
                                 @RequestParam("txtFecha") Date fecha,
                                 @RequestParam("SelectTable") int propietario) throws Exception {

        Documento doc = new Documento();

        String nombreArchivoNuevo = UUID.randomUUID().toString();

        byte[] bytes = file.getBytes();

        String nombreArchivoOriginal = file.getOriginalFilename();

        String archivoExtension = nombreArchivoOriginal.substring(nombreArchivoOriginal.lastIndexOf("."));

        String nuevoNomArchivo = nombreArchivoNuevo + archivoExtension;

        StringBuilder builder = new StringBuilder();
        builder.append(System.getProperty("user.home"));
        builder.append(File.separator);
        builder.append("file-system-soii");


        File folder = new File(builder.toString());
        if (!folder.exists()) {
            folder.mkdirs();
        }
        builder.append(File.separator);
        builder.append(idenDoc + "-" + propietario);

        File folder2 = new File(builder.toString());
        if (!folder2.exists()) {
            folder2.mkdirs();
        }

        Path path = Paths.get(builder.append(File.separator) + nuevoNomArchivo);
        Files.write(path, bytes);

        doc.setIdDoc(idenDoc);
        doc.setPropietario(propietario);
        doc.setFechaDoc(fecha);
        doc.setDocGuardado(path.toString());

        documentoDao.guardarDocumento(doc);
    }

    @RequestMapping(value = "api/descargarDocumento", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<List<Documento>> descargarDocumento(@RequestBody Documento documento) {
        List<Documento> documentos = documentoDao.descargarDocumento(documento);
        return ResponseEntity.ok(documentos);
    }

}