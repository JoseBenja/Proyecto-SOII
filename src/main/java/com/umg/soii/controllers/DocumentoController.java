package com.umg.soii.controllers;

import com.umg.soii.dao.DocumentoDao;
import com.umg.soii.dao.PropietarioDao;
import com.umg.soii.models.Documento;
import com.umg.soii.models.Propietario;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.engine.jdbc.env.internal.LobCreatorBuilderImpl;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class DocumentoController {

    @Autowired
    private DocumentoDao documentoDao;

    private static final Logger logger = Logger.getLogger(UsuarioController.class.getName());

    @PostMapping("/cargarDocumento")
    public void guardarDocumento(@RequestParam("idFile") MultipartFile file,
                                 @RequestParam("txtIdDoc") String idenDoc,
                                 @RequestParam("txtFecha") Date fecha,
                                 @RequestParam("SelectTable") int propietario,
                                 HttpServletResponse response) throws Exception {

        Documento doc = new Documento();

        byte[] bytes = file.getBytes();

        String nombreArchivoOriginal = file.getOriginalFilename();


        StringBuilder builder = new StringBuilder();
        builder.append(System.getProperty("user.home"));
        builder.append(File.separator);
        builder.append("file-system-soii");


        File folder = new File(builder.toString());
        if (!folder.exists()) {
            logger.log(Level.INFO,"File System creado con exito");
            folder.mkdirs();
        }
        builder.append(File.separator);
        builder.append(idenDoc + "-" + propietario + "-" + fecha);

        File folder2 = new File(builder.toString());
        if (!folder2.exists()) {

            folder2.mkdirs();
        }

        Path path = Paths.get(builder.append(File.separator) + nombreArchivoOriginal);
        Files.write(path, bytes);


        doc.setIdDoc(idenDoc);
        doc.setPropietario(propietario);
        doc.setFechaDoc(fecha);
        doc.setDocGuardado(path.toString());

        documentoDao.guardarDocumento(doc);
        logger.log(Level.INFO,"Documento guardado con exito");

        response.sendRedirect("/menuPrincipal.html");
    }

    @RequestMapping(value = "api/descargarDocumento", method = RequestMethod.POST)
    public List<Documento> descargarDocumento(@RequestBody Documento documento) {
        return documentoDao.descargarDocumento(documento);
    }
}