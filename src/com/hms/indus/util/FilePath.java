package com.hms.indus.util;

import java.io.File;

import com.hms.indus.util.OSValidator;

public enum FilePath {

	BASEPATH(System.getProperty("catalina.home") + "/indus/"); // change this path whenever need (at installation time)
	String path;
	static String filePath;

	private FilePath(String path) {
		this.path = path;
	}

	static String commonWindowsPath = "D:\\EHRLive\\";
	static String commonLinuxPath =  "/home/amol/Documents/EHRLive/";//"/home/administrator/Documents/EHRLive/";

	// For reports Upload
	public static String getBasePath() {
		if (OSValidator.isWindows()) {
			filePath = commonWindowsPath + "Reports\\";
		} else if (OSValidator.isMac()) {
			System.out.println("This is Mac");
		} else if (OSValidator.isUnix()) {
			filePath = commonLinuxPath + "Reports/";
		} else if (OSValidator.isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Your OS is not support!!");
		}
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return filePath;
	}

	// For Content Upload
	public static String getContentPath() {
		if (OSValidator.isWindows()) {
			filePath = commonWindowsPath + "Content Upload\\";
		} else if (OSValidator.isMac()) {
			System.out.println("This is Mac");
		} else if (OSValidator.isUnix()) {
			filePath = commonLinuxPath + "Content Upload/";
		} else if (OSValidator.isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Your OS is not support!!");
		}
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return filePath;
	}

	// For Video Testomonial Upload
	public static String getTestimonialPath() {
		if (OSValidator.isWindows()) {
			filePath = commonWindowsPath + "Testimonial\\";
		} else if (OSValidator.isMac()) {
			System.out.println("This is Mac");
		} else if (OSValidator.isUnix()) {
			filePath = commonLinuxPath + "Testimonial/";
		} else if (OSValidator.isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Your OS is not support!!");
		}
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return filePath;
	}

	// For Profile Pictures Upload
	public static String getProfilepicturePath() {
		if (OSValidator.isWindows()) {
			filePath = commonWindowsPath + "Profile Pictures\\";
		} else if (OSValidator.isMac()) {
			System.out.println("This is Mac");
		} else if (OSValidator.isUnix()) {
			filePath = commonLinuxPath + "Profile Pictures/";
		} else if (OSValidator.isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Your OS is not support!!");
		}
		return filePath;
	}

	// For Self Upload
	public static String getSelfUploadPath() {
		if (OSValidator.isWindows()) {
			filePath = commonWindowsPath + "Self Upload\\";
		} else if (OSValidator.isMac()) {
			System.out.println("This is Mac");
		} else if (OSValidator.isUnix()) {
			filePath = commonLinuxPath + "Self Upload/";
		} else if (OSValidator.isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Your OS is not support!!");
		}
		return filePath;
	}

	// For Bunch Upload Reports
	public static String getBunchReportPath() {
		if (OSValidator.isWindows()) {
			filePath = commonWindowsPath + "Imported Reports\\";
		} else if (OSValidator.isMac()) {
			System.out.println("This is Mac");
		} else if (OSValidator.isUnix()) {
			filePath = commonLinuxPath + "Imported Reports/";
		} else if (OSValidator.isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Your OS is not support!!");
		}
		return filePath;
	}

	// For Bunch Upload Data Entry
	public static String getBunchDataEntryPath() {
		if (OSValidator.isWindows()) {
			filePath = commonWindowsPath + "Imported DataEntry\\";
		} else if (OSValidator.isMac()) {
			System.out.println("This is Mac");
		} else if (OSValidator.isUnix()) {
			filePath = commonLinuxPath + "Imported DataEntry/";
		} else if (OSValidator.isSolaris()) {
			System.out.println("This is Solaris");
		} else {
			System.out.println("Your OS is not support!!");
		}
		return filePath;
	}

}