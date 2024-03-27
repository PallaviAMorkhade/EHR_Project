package com.hms.indus.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Amol Saware
 */
public class TomcatAccessLogCleaner extends TimerTask {

	private static final Log log = LogFactory.getLog(TomcatAccessLogCleaner.class);

	private static final String DEFAULT_LOG_FILE_PATTERN = "localhost_access_log\\.yyyy-MM-dd\\.txt";
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	private String dateFormat;
	private Pattern logFilePat;
	private File logFileDir;
	private int numBackups;

	public TomcatAccessLogCleaner(File logFileDir, int numBackups) {
		this(logFileDir, DEFAULT_LOG_FILE_PATTERN, DEFAULT_DATE_FORMAT, numBackups);
	}

	public TomcatAccessLogCleaner(File logFileDir, String logFilePattern, String dateFormat, int numBackups) {
		this.dateFormat = dateFormat;
		this.logFileDir = logFileDir;
		String pat = logFilePattern.replace(dateFormat, "(.+?)");
		logFilePat = Pattern.compile(pat);
		this.numBackups = numBackups;
	}

	public void clean() {
		log.info("Starting to clean old Tomcat access logs. Number of backups to keep: " + numBackups);
		File[] files = logFileDir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String file) {
				return logFilePat.matcher(file).matches();
			}
		});

		List<LogFile> logFiles = new ArrayList<LogFile>(files.length);
		for (File file : files) {
			try {
				LogFile logFile = new LogFile(file, logFilePat, dateFormat);
				logFiles.add(logFile);
			} catch (ParseException pe) {
			}
		}

		Collections.sort(logFiles, new Comparator<LogFile>() {
			@Override
			public int compare(LogFile o1, LogFile o2) {
				return o1.getLogDate().compareTo(o2.getLogDate());
			}
		});

		int numFilesToClean = logFiles.size() - numBackups;
		int removed = 0;
		for (int i = 0; i < numFilesToClean; i++) {
			LogFile logFile = logFiles.get(i);
			log.debug("Deleting access log file: " + logFile);
			if (!logFile.getFile().delete()) {
				log.warn("Failed deleting log file");
			}
		}

		log.info("Finished cleaning old Tomcat access logs. Total log files: " + logFiles.size() + ". Deleted: "
				+ removed + " of " + Math.max(0, numFilesToClean));
	}

	public static class LogFile {
		private File file;
		private Date logDate;

		public LogFile(File file, Pattern pattern, String dateFormat) throws ParseException {
			Matcher matcher = pattern.matcher(file.getName());
			if (matcher.find()) {
				String dateStr = matcher.group(1);
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				logDate = sdf.parse(dateStr);
				this.file = file;
			}
		}

		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}

		public Date getLogDate() {
			return logDate;
		}

		public void setLogDate(Date logDate) {
			this.logDate = logDate;
		}
	}

	public void run() {
		clean();
	}
}