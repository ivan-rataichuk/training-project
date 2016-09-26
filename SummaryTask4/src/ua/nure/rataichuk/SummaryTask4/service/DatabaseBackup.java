package ua.nure.rataichuk.SummaryTask4.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

import org.apache.log4j.Logger;

/**
 * DB Backup service
 * 
 * @author Ivan Rataichuk
 *
 */
public class DatabaseBackup extends Thread {
	
	private static final Logger LOG = Logger.getLogger(DatabaseBackup.class);

	private static final long SLEEP_TIME = 1000 * 10;

	private boolean stop = false;

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public void run() {
		while (!stop) {
			try {
				Thread.sleep(SLEEP_TIME);
				backupToSQL();
			} catch (InterruptedException | URISyntaxException | IOException e) {
				LOG.error(e.getCause());
				LOG.error(e.getMessage());
			}

		}
	}

	private void backupToSQL() throws URISyntaxException, IOException, InterruptedException {
		CodeSource codeSource = DatabaseBackup.class.getProtectionDomain().getCodeSource();
		File jarFile = new File(codeSource.getLocation().toURI().getPath());
		String jarDir = jarFile.getParentFile().getPath();

		String dbName = "webapp";
		String dbUser = "air";
		String dbPass = "air051088";

		/* NOTE: Creating Path Constraints for folder saving */
		/* NOTE: Here the backup folder is created for saving inside it */
		String folderPath = jarDir + "\\backup";

		/* NOTE: Creating Folder if it does not exist */
		File f1 = new File(folderPath);
		f1.mkdir();

		/* NOTE: Creating Path Constraints for backup saving */
		/*
		 * NOTE: Here the backup is saved in a folder called backup with the
		 * name backup.sql
		 */
		String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";

		/* NOTE: Used to create a cmd command */
		String executeCmd = "mysqldump -u" + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;

		/* NOTE: Executing the command here */
		Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
		int processComplete = runtimeProcess.waitFor();

		/*
		 * NOTE: processComplete=0 if correctly executed, will contain other
		 * values if not
		 */
		if (processComplete == 0) {
			LOG.trace("Backup Complete");
		} else {
			LOG.trace("Backup Failure");
		}
	}
}
