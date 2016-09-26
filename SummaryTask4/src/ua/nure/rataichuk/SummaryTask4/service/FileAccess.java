package ua.nure.rataichuk.SummaryTask4.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

/**
 * File system operations
 * 
 * @author Ivan Rataichuk
 *
 */
public class FileAccess {
	
	private static final Logger LOG = Logger.getLogger(FileAccess.class);
	
    private static final String UPLOAD_DIRECTORY = "upload";
 
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 5; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 10; // 50MB
	
	/**
	 * Uploads file to specified directory and returns relative path to it.
	 * 
	 * @param request
	 * @param realPath Full path on disc
	 * @return String relative path to file
	 */
	public String uploadFile(HttpServletRequest request, String realPath) {
		String storedFile = null;
        
        DiskFileItemFactory factory = new DiskFileItemFactory();

        factory.setSizeThreshold(MEMORY_THRESHOLD);

        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        String uploadPath = realPath + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        try {

            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                    	
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        storedFile = UPLOAD_DIRECTORY + File.separator + fileName;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        request.setAttribute("message",
                            "Upload has been done successfully!");
                        
                    }
                }
            }
        } catch (Exception ex) {
			LOG.error(ex.getMessage());
        }
        return storedFile;
	}
}
